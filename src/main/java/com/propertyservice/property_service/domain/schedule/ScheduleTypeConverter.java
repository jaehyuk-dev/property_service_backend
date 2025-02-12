package com.propertyservice.property_service.domain.schedule;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ScheduleTypeConverter implements AttributeConverter<ScheduleType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ScheduleType scheduleType) {
        return (scheduleType != null) ? scheduleType.getValue() : null;
    }

    @Override
    public ScheduleType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? ScheduleType.fromValue(dbData) : null;
    }
}
