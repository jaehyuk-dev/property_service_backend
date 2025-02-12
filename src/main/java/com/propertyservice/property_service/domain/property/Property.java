package com.propertyservice.property_service.domain.property;

import com.propertyservice.property_service.domain.common.BaseEntity;
import com.propertyservice.property_service.domain.common.eums.TransactionType;
import com.propertyservice.property_service.domain.common.eums.TransactionTypeConverter;
import com.propertyservice.property_service.domain.property.enums.HeatingType;
import com.propertyservice.property_service.domain.property.enums.HeatingTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "property")
public class Property extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "owner_name", length = 255)
    private String ownerName;

    @Column(name = "owner_phone_number", nullable = false, length = 255)
    private String ownerPhoneNumber;

    @Column(name = "owner_relation", length = 255)
    private String ownerRelation;

    @Column(name = "property_room_number", nullable = false, length = 255)
    private String roomNumber;

    @Column(name = "property_type", nullable = false, length = 255)
    private String propertyType;

    @Column(name = "property_floor", length = 255)
    private String propertyFloor;

    @Column(name = "room_bath_count", nullable = false, length = 255)
    private String roomBathCount;

    @Column(name = "main_room_direction", nullable = false, length = 64)
    private String mainRoomDirection;

    @Column(name = "exclusive_area")
    private Double exclusiveArea;

    @Column(name = "supply_area")
    private Double supplyArea;

    @Column(name = "approval_date", nullable = false)
    private LocalDateTime approvalDate;

    @Column(name = "move_in_date", nullable = false)
    private LocalDateTime availableMoveInDate;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(name = "property_transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "property_price1", nullable = false)
    private Long propertyPrice1;

    @Column(name = "property_price2")
    private Long propertyPrice2;

    @Column(name = "maintenace_price")
    private Long maintenancePrice;

    @Column(name = "parking_available", nullable = false)
    private Boolean parkingAvailable = false;

    @Convert(converter = HeatingTypeConverter.class)
    @Column(name = "heating_type", nullable = false)
    private HeatingType heatingType;
}

