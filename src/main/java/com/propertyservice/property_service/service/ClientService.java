package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ClientExpectedTransactionType;
import com.propertyservice.property_service.domain.client.ClientRemark;
import com.propertyservice.property_service.domain.client.ShowingProperty;
import com.propertyservice.property_service.domain.client.enums.ClientStatus;
import com.propertyservice.property_service.domain.common.eums.Gender;
import com.propertyservice.property_service.domain.common.eums.TransactionType;
import com.propertyservice.property_service.domain.office.OfficeUser;
import com.propertyservice.property_service.domain.property.Building;
import com.propertyservice.property_service.domain.property.Property;
import com.propertyservice.property_service.domain.revenue.Revenue;
import com.propertyservice.property_service.dto.client.*;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import com.propertyservice.property_service.repository.client.ClientExpectedTransactionTypeRepository;
import com.propertyservice.property_service.repository.client.ClientRemarkRepository;
import com.propertyservice.property_service.repository.client.ClientRepository;
import com.propertyservice.property_service.repository.client.ShowingPropertyRepository;
import com.propertyservice.property_service.repository.office.OfficeUserRepository;
import com.propertyservice.property_service.repository.property.PropertyRepository;
import com.propertyservice.property_service.repository.revenue.RevenueRepository;
import com.propertyservice.property_service.repository.schedule.ScheduleRepository;
import com.propertyservice.property_service.utils.DateTimeUtil;
import com.propertyservice.property_service.utils.PriceFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientExpectedTransactionTypeRepository clientExpectedTransactionTypeRepository;
    private final ClientRemarkRepository clientRemarkRepository;
    private final AuthService authService;
    private final ScheduleRepository scheduleRepository;
    private final OfficeUserRepository officeUserRepository;
    private final ShowingPropertyRepository showingPropertyRepository;
    private final PropertyRepository propertyRepository;
    private final RevenueRepository revenueRepository;

    @Transactional
    public void registerClient(ClientRegisterRequest request) {
        Client newClient = saveClientInfo(request);
        saveClientExpectedTransactionType(request, newClient);
        saveClientRemark(request.getRemark(), newClient);
    }

    private Client saveClientInfo(ClientRegisterRequest request) {
        System.out.println("authService.getCurrentUserEntity().getName() = " + authService.getCurrentUserEntity().getName());
        return clientRepository.save(
                Client.builder()
                        .picUser(authService.getCurrentUserEntity())
                        .status(ClientStatus.CONSULTING)
                        .name(request.getName())
                        .phoneNumber(request.getPhoneNumber())
                        .gender(Gender.fromValue(request.getGenderCode()))
                        .source(request.getSource())
                        .type(request.getType())
                        .moveInDate(request.getMoveInDate())
                        .build()
        );
    }

    private void saveClientExpectedTransactionType(ClientRegisterRequest request, Client newClient) {
        for (Integer i : request.getExpectedTransactionTypeCodeList()) {
            clientExpectedTransactionTypeRepository.save(
                    ClientExpectedTransactionType.builder()
                            .client(newClient)
                            .expectedTransactionType(TransactionType.fromValue(i))
                            .build()
            );
        }
    }

    private void saveClientRemark(String remark, Client newClient) {
        clientRemarkRepository.save(
                ClientRemark.builder()
                        .client(newClient)
                        .remark(remark)
                        .build()
        );
    }


    public List<ClientSummaryResponse> searchClientSummaryList (ClientSearchCondition condition){
        return clientRepository.searchClientSummaryList(condition, authService.getCurrentUserEntity().getOffice().getOfficeId());
    }


    public ClientDetailResponse searchClientDetail(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<ClientScheduleDto> clientScheduleList = getClientScheduleDtoList(client);

        List<ClientRemarkDto> clientRemarkList = getClientRemarkDtoList(client);

        List<ShowingPropertyDto> showingPropertyDtoList = getShowingPropertyDtoList(client);

        return ClientDetailResponse.builder()
                .clientId(client.getId())
                .clientName(client.getName())
                .picUserName(client.getPicUser().getName())
                .clientStatus(client.getStatus().getLabel())
                .clientSource(client.getSource())
                .clientType(client.getType())
                .clientExpectedMoveInDate(client.getMoveInDate())
                .clientScheduleList(clientScheduleList)
                .showingPropertyList(showingPropertyDtoList)
                .clientRemarkList(clientRemarkList)
                .build();
    }

    private List<ShowingPropertyDto> getShowingPropertyDtoList(Client client) {
        List<ShowingPropertyDto> showingPropertyDtoList = new ArrayList<>();

        showingPropertyRepository.findByClient(client).forEach(showingProperty -> {
            Property property = showingProperty.getProperty();
            Building building = property.getBuilding();

            String formattedPrice = PriceFormatter.format(property.getPropertyPrice1(), property.getPropertyPrice2(), property.getTransactionType());

            showingPropertyDtoList.add(
                    ShowingPropertyDto.builder()
                            .showingPropertyId(showingProperty.getId())
                            .propertyTransactionType(property.getTransactionType().getLabel())
                            .propertyPrice(formattedPrice)
                            .propertyType(property.getPropertyType())
                            .propertyAddress(building.getAddress())
                            .build()
           );
        });
        return showingPropertyDtoList;
    }

    private List<ClientScheduleDto> getClientScheduleDtoList(Client client) {
        List<ClientScheduleDto> clientScheduleList = new ArrayList<>();
        scheduleRepository.findByClientOrderByDateDesc(client).forEach(schedule -> {
            clientScheduleList.add(
                    ClientScheduleDto.builder()
                            .scheduleId(schedule.getId())
                            .picUser(schedule.getPicUser().getName())
                            .date(DateTimeUtil.formatYearMonthDayHourMin(schedule.getDate()))
                            .scheduleType(schedule.getScheduleType().getLabel())
                            .remark(schedule.getRemark())
                            .isCompleted(schedule.isCompleted())
                            .build()
            );
        });
        return clientScheduleList;
    }

    private List<ClientRemarkDto> getClientRemarkDtoList(Client client) {
        List<ClientRemarkDto> clientRemarkList = new ArrayList<>();
        clientRemarkRepository.findByClient(client).forEach(clientRemark -> {
            clientRemarkList.add(
                    ClientRemarkDto.builder()
                            .remarkId(clientRemark.getId())
                            .remark(clientRemark.getRemark())
                            .createdBy(
                                    officeUserRepository.findById(clientRemark.getCreatedByUserId())
                                            .map(OfficeUser::getName) // `Optional`이 비어 있으면 자동으로 `null` 반환
                                            .orElse(null) // `null`이면 createdBy 필드가 비어 있게 유지
                            )
                            .createdTime(DateTimeUtil.formatYearMonthDayHourMin(clientRemark.getCreatedDate()))
                            .build()
            );
        });
        return clientRemarkList;
    }

    @Transactional
    public void createClientRemark(ClientRemarkRequest request){
        clientRemarkRepository.save(
               ClientRemark.builder()
                       .client(clientRepository.findById(request.getClientId()).orElseThrow(
                               () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
                       ))
                       .remark(request.getRemark())
                       .build()
        );
    }

    @Transactional
    public void removeClientRemark(Long clientId){
        clientRemarkRepository.deleteById(clientId);
    }

    @Transactional
    public void createShowingProperty(ShowingPropertyRegisterRequest request) {
        showingPropertyRepository.save(
                ShowingProperty.builder()
                        .client(clientRepository.findById(request.getClientId()).orElseThrow(
                                () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
                        ))
                        .property(propertyRepository.findById(request.getPropertyId()).orElseThrow(
                                () -> new BusinessException(ErrorCode.PROPERTY_NOT_FOUND)
                        ))
                        .build()
        );
    }

    @Transactional
    public void removeShowingProperty(Long showingPropertyId){
        scheduleRepository.deleteById(showingPropertyId);
    }

    @Transactional
    public void updateClientDetail(ClientDetailUpdateRequest request) {
        Client client = clientRepository.findById(request.getClientId()).orElseThrow(
                () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
        );

        client.updateClientDetail(
                request.getClientName(),
                request.getClientPhoneNumber(),
                request.getClientSource(),
                request.getClientType(),
                request.getClientExpectedMoveInDate()
        );

        clientExpectedTransactionTypeRepository.deleteByClient(client);

        request.getClientExpectedTransactionTypeList().forEach(transactionType -> {
            clientExpectedTransactionTypeRepository.save(
                    ClientExpectedTransactionType.builder()
                            .client(client)
                            .expectedTransactionType(TransactionType.valueOf(transactionType))
                            .build()
            );
        });
    }

    @Transactional
    public void updateClientStatus(ClientStatusUpdateRequest request) {
        clientRepository.findById(request.getClientId()).orElseThrow(
                () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
        ).updateClientStatus(
                ClientStatus.fromValue(request.getNewStatusCode())
        );
        if(ClientStatus.fromValue(request.getNewStatusCode()).equals(ClientStatus.CONTRACT_COMPLETED)){
            revenueRepository.save(
                    Revenue.builder()
                            .showingProperty(showingPropertyRepository.findById(request.getShowingPropertyId()).orElseThrow(
                                    () -> new BusinessException(ErrorCode.PROPERTY_NOT_FOUND)
                            ))
                            .client(clientRepository.findById(request.getClientId()).orElseThrow(
                                    () -> new BusinessException(ErrorCode.CLIENT_NOT_FOUND)
                            ))
                            .property(propertyRepository.findById(request.getPropertyId()).orElseThrow(
                                    () -> new BusinessException(ErrorCode.PROPERTY_NOT_FOUND)
                            ))
                            .commissionFee(request.getCommissionFee())
                            .moveInDate(request.getMoveInDate())
                            .moveOutDate(request.getMoveOutDate())
                            .build()
            );
        }
    }
}
