package com.propertyservice.property_service.domain.property.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MaintenanceItemTypeConverter implements AttributeConverter<MaintenanceItemType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MaintenanceItemType maintenanceItemType) {
        return (maintenanceItemType != null) ? maintenanceItemType.getValue() : null;
    }

    @Override
    public MaintenanceItemType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? MaintenanceItemType.fromValue(dbData) : null;
    }
}