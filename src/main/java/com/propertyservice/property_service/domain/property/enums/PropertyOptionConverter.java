package com.propertyservice.property_service.domain.property.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PropertyOptionConverter implements AttributeConverter<PropertyOptionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PropertyOptionType propertyOption) {
        return (propertyOption != null) ? propertyOption.getValue() : null;
    }

    @Override
    public PropertyOptionType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? PropertyOptionType.fromValue(dbData) : null;
    }
}
