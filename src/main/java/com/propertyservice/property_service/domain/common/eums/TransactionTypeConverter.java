package com.propertyservice.property_service.domain.common.eums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionType transactionType) {
        return (transactionType != null) ? transactionType.getValue() : null;
    }

    @Override
    public TransactionType convertToEntityAttribute(Integer dbData) {
        return (dbData != null) ? TransactionType.fromValue(dbData) : null;
    }
}
