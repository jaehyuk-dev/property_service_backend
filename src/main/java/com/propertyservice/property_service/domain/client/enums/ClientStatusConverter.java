package com.propertyservice.property_service.domain.client.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClientStatusConverter implements AttributeConverter<ClientStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ClientStatus clientStatus) {
        if(clientStatus == null) {
            return null;
        }
        return clientStatus.getValue();
    }

    @Override
    public ClientStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return ClientStatus.fromValue(dbData); // DB에서 가져온 정수 값을 Enum으로 변환
    }
}