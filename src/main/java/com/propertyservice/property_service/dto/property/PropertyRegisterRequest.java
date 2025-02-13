package com.propertyservice.property_service.dto.property;

import com.propertyservice.property_service.domain.common.eums.TransactionType;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PropertyRegisterRequest {
    private long buildingId;

    private String ownerName;
    private String ownerPhoneNumber;
    private String ownerRelation;

    private String roomNumber;
    private String propertyType;
    private String propertyFloor;
    private String roomBathCount;
    private String mainRoomDirection;
    private Double exclusiveArea;
    private Double supplyArea;
    private LocalDate approvalDate;
    private LocalDate availableMoveInDate;

    private int transactionTypeCode;

    private Long propertyPrice1;
    private Long propertyPrice2;

    private boolean parkingAvailable;

    private Long maintenancePrice;
    private List<Integer> maintenanceItemCodeList;

    private List<Integer> optionItemCodeList;

    private int heatingTypeCode;

    @Min(value = 1)
    private List<MultipartFile> propertyImages;
    private Integer representativeImageIndex;
}
