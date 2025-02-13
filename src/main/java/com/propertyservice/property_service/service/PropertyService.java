package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.common.eums.TransactionType;
import com.propertyservice.property_service.domain.property.*;
import com.propertyservice.property_service.domain.property.enums.BuildingType;
import com.propertyservice.property_service.domain.property.enums.HeatingType;
import com.propertyservice.property_service.domain.property.enums.MaintenanceItemType;
import com.propertyservice.property_service.domain.property.enums.PropertyOptionType;
import com.propertyservice.property_service.dto.property.*;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import com.propertyservice.property_service.repository.property.*;
import com.propertyservice.property_service.utils.PriceFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final BuildingPhotoRepository buildingPhotoRepository;
    private final MaintenanceItemRepository maintenanceItemRepository;
    private final PropertyOptionRepository propertyOptionRepository;
    private final PropertyPhotoRepository propertyPhotoRepository;

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
        Building building = buildingRepository.save(
                Building.builder()
                        .picUSer(authService.getCurrentUserEntity())
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

        List<MultipartFile> images = request.getBuildingImages();
        if (images != null && images.size() > 3) {
            throw new BusinessException(ErrorCode.MAX_BUILDING_PHOTO_LENGTH);
        }

        Integer repIndex = request.getRepresentativeImageIndex();
        if (repIndex != null && (repIndex < 0 || repIndex >= images.size())) {
            throw new BusinessException(ErrorCode.INVALID_REPRESENTATION_PHOTO);
        }

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                boolean isRepresentative = (repIndex != null && repIndex == i);
                String imageUrl = saveBuildingImage(building.getId(), images.get(i));
                buildingPhotoRepository.save(
                        BuildingPhoto.builder()
                                .building(building)
                                .photoUrl(imageUrl)
                                .isMain(isRepresentative)
                                .build()
                );
            }
        }
    }


    // 건물 이미지 저장 메서드
    private String saveBuildingImage(Long buildingId, MultipartFile file) {
        try {
            String directory = "uploads/buildings/" + buildingId;
            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = dirPath.resolve(filename);
            Files.write(filePath, file.getBytes());

            return "/uploads/buildings/" + buildingId + "/" + filename; // 저장된 이미지 URL 리턴
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.IMAGE_ERROR);
        }
    }

    @Transactional
    public void updateBuilding(BuildingUpdateRequest request) {
        buildingRepository.findById(request.getBuildingId()).orElseThrow(
                ()-> new BusinessException(ErrorCode.BUILDING_NOT_FOUND)
        ).updateBuilding(
                request.getZonecode(),
                request.getAddress(),
                request.getJibunAddress(),
                request.getParkingSpace(),
                request.getFloorCount(),
                request.getMainDoorDirection(),
                request.getCompletionYear(),
                request.getBuildingTypeCode(),
                request.getElevatorCount(),
                request.isHasIllegal(),
                request.getCommonPassword()
        );
    }

    public List<BuildingSummaryResponse> searchBuildingSummaryList(BuildingSearchCondition condition) {
        return List.of(null);
    }

    @Transactional
    public void registerProperty(PropertyRegisterRequest request) {
        Building building = buildingRepository.findById(request.getBuildingId()).orElseThrow(
                () -> new BusinessException(ErrorCode.BUILDING_NOT_FOUND)
        );

        Property property = propertyRepository.save(
                Property.builder()
                        .picUser(authService.getCurrentUserEntity())
                        .building(building)
                        .ownerName(request.getOwnerName())
                        .ownerPhoneNumber(request.getOwnerPhoneNumber())
                        .ownerRelation(request.getOwnerRelation())
                        .roomNumber(request.getRoomNumber())
                        .propertyType(request.getPropertyType())
                        .propertyFloor(request.getPropertyFloor())
                        .roomBathCount(request.getRoomBathCount())
                        .mainRoomDirection(request.getMainRoomDirection())
                        .exclusiveArea(request.getExclusiveArea())
                        .supplyArea(request.getSupplyArea())
                        .approvalDate(request.getApprovalDate())
                        .availableMoveInDate(request.getAvailableMoveInDate())
                        .transactionType(TransactionType.fromValue(request.getTransactionTypeCode()))
                        .propertyPrice1(request.getPropertyPrice1())
                        .propertyPrice2(request.getPropertyPrice2())
                        .parkingAvailable(request.isParkingAvailable())
                        .heatingType(HeatingType.fromValue(request.getHeatingTypeCode()))
                        .build()
        );

        request.getMaintenanceItemCodeList().forEach(maintenanceItemCode ->
                        maintenanceItemRepository.save(
                                MaintenanceItem.builder()
                                        .property(property)
                                        .maintenanceItem(MaintenanceItemType.fromValue(maintenanceItemCode))
                                        .build()
                        )
        );

        request.getOptionItemCodeList().forEach(option->
            propertyOptionRepository.save(
                    PropertyOption.builder()
                            .property(property)
                            .optionType(PropertyOptionType.fromValue(option))
                            .build()
            )
        );

        List<MultipartFile> images = request.getPropertyImages();
        if (images != null && images.size() > 3) {
            throw new BusinessException(ErrorCode.MAX_PROPERTY_PHOTO_LENGTH);
        }

        Integer repIndex = request.getRepresentativeImageIndex();
        if (repIndex != null && (repIndex < 0 || repIndex >= images.size())) {
            throw new BusinessException(ErrorCode.INVALID_REPRESENTATION_PHOTO);
        }

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                boolean isRepresentative = (repIndex != null && repIndex == i);
                String imageUrl = savePropertyImage(building.getId(), images.get(i));
                propertyPhotoRepository.save(
                        PropertyPhoto.builder()
                                .property(property)
                                .photoUrl(imageUrl)
                                .isMain(isRepresentative)
                                .build()
                );
            }
        }
    }

    // 매물이미지 저장 메서드
    private String savePropertyImage(Long propertyId, MultipartFile file) {
        try {
            String directory = "uploads/properties/" + propertyId;
            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = dirPath.resolve(filename);
            Files.write(filePath, file.getBytes());

            return "/uploads/properties/" + propertyId + "/" + filename; // 저장된 이미지 URL 리턴
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.IMAGE_ERROR);
        }
    }
}
