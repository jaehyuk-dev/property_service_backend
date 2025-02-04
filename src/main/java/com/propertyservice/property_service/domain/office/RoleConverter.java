package com.propertyservice.property_service.domain.office;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getValue(); // Enum의 숫자 값(10, 20)을 DB에 저장
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Role.fromValue(dbData); // DB에서 가져온 정수 값을 Enum으로 변환
    }
}