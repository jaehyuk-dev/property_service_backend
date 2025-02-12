package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.property.Building;
import com.propertyservice.property_service.domain.property.Property;
import com.propertyservice.property_service.domain.property.enums.BuildingType;
import com.propertyservice.property_service.dto.property.BuildingRegisterRequest;
import com.propertyservice.property_service.dto.property.PropertySearchCondition;
import com.propertyservice.property_service.dto.property.PropertySummaryResponse;
import com.propertyservice.property_service.repository.property.BuildingRepository;
import com.propertyservice.property_service.repository.property.PropertyRepository;
import com.propertyservice.property_service.utils.PriceFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final AuthService authService;
    private final BuildingRepository buildingRepository;

    public List<PropertySummaryResponse> searchPropertySummaryList(PropertySearchCondition condition) {
        List<PropertySummaryResponse> propertySummaryResponseList = new ArrayList<>();
        for (Property property : propertyRepository.searchPropertySummaryList(condition, authService.getCurrentUserEntity().getOffice().getOfficeId())) {
            Building building = property.getBuilding();
            String formattedPrice = PriceFormatter.format(property.getPropertyPrice1(), property.getPropertyPrice2(), property.getTransactionType());

            propertySummaryResponseList.add(
                    PropertySummaryResponse.builder()
                            .propertyId(property.getId())
                            .propertyTransactionType(property.getTransactionType().getLabel())
                            .propertyPrice(formattedPrice)
                            .propertyType(property.getPropertyType())
                            .propertyAddress(building.getAddress())
                            .build()
            );
        }
        return propertySummaryResponseList;
    }

    @Transactional
    public void registerBuilding(BuildingRegisterRequest request) {
        buildingRepository.save(
                Building.builder()
                        .name(request.getBuildingName())
                        .zoneCode(request.getZonecode())
                        .address(request.getBuildingAddress())
                        .jibunAddress(request.getJibunAddress())
                        .parkingSpace(request.getParkSpace())
                        .floorCount(request.getFloorCount())
                        .mainDoorDirection(request.getMainDoorDirection())
                        .completionYear(request.getCompletionYear())
                        .buildingType(BuildingType.fromValue(request.getBuildingTypeCode()))
                        .elevatorCount(request.getElevatorCount())
                        .hasIllegal(request.isHasIllegal())
                        .commonPassword(request.getCommonPassword())
                        .build()
        );
    }
}
