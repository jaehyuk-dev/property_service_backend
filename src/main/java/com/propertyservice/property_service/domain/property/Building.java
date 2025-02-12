package com.propertyservice.property_service.domain.property;

import com.propertyservice.property_service.domain.common.BaseEntity;
import com.propertyservice.property_service.domain.property.enums.BuildingType;
import com.propertyservice.property_service.domain.property.enums.BuildingTypeConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "building")
public class Building extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "building_name", length = 255)
    private String name;

    @Column(name = "zonecode", nullable = false, length = 5)
    private String zoneCode;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "jibun_address", length = 255)
    private String jibunAddress;

    @Column(name = "parking_space", nullable = false)
    private Short parkingSpace;

    @Column(name = "floor_count", nullable = false, length = 255)
    private String floorCount;

    @Column(name = "main_door_direction", length = 64)
    private String mainDoorDirection;

    @Column(name = "completion_year", nullable = false)
    private Short completionYear;

    @Convert(converter = BuildingTypeConverter.class)
    @Column(name = "building_type", nullable = false)
    private BuildingType buildingType; // ✅ 31(주거용), 32(비주거용)

    @Column(name = "elevator_count", nullable = false)
    private Short elevatorCount;

    @Column(name = "has_illegal", nullable = false)
    private Boolean hasIllegal = false;

    @Column(name = "common_password", length = 255)
    private String commonPassword;

    @Builder
    public Building(String name, String zoneCode, String address, String jibunAddress, Short parkingSpace, String floorCount, String mainDoorDirection, Short completionYear, BuildingType buildingType, Short elevatorCount, Boolean hasIllegal, String commonPassword) {
        this.name = name;
        this.zoneCode = zoneCode;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.parkingSpace = parkingSpace;
        this.floorCount = floorCount;
        this.mainDoorDirection = mainDoorDirection;
        this.completionYear = completionYear;
        this.buildingType = buildingType;
        this.elevatorCount = elevatorCount;
        this.hasIllegal = hasIllegal;
        this.commonPassword = commonPassword;
    }
}
