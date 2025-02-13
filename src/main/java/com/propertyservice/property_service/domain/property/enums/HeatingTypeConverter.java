package com.propertyservice.property_service.domain.property.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HeatingTypeConverter implements AttributeConverter<HeatingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(HeatingType heatingType) {
        return (heatingType != null) ? heatingType.getValue() : null;
    }

    @Override
    public HeatingType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? HeatingType.fromValue(dbData) : null;
    }
}
