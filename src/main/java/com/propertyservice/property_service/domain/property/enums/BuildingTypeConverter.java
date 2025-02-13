package com.propertyservice.property_service.domain.property.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BuildingTypeConverter implements AttributeConverter<BuildingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BuildingType buildingType) {
        return (buildingType != null) ? buildingType.getValue() : null;
    }

    @Override
    public BuildingType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? BuildingType.fromValue(dbData) : null;
    }
}
