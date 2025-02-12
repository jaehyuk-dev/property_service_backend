package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ClientExpectedTransactionType;
import com.propertyservice.property_service.domain.client.ClientRemark;
import com.propertyservice.property_service.domain.client.ClientStatus;
import com.propertyservice.property_service.domain.common.Gender;
import com.propertyservice.property_service.domain.common.TransactionType;
import com.propertyservice.property_service.dto.client.ClientRegisterRequest;
import com.propertyservice.property_service.dto.client.ClientSearchCondition;
import com.propertyservice.property_service.dto.client.ClientSummaryResponse;
import com.propertyservice.property_service.repository.client.ClientExpectedTransactionTypeRepository;
import com.propertyservice.property_service.repository.client.ClientRemarkRepository;
import com.propertyservice.property_service.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
