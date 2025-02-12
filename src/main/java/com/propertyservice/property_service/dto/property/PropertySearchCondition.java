package com.propertyservice.property_service.dto.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertySearchCondition {
    private String searchType;  // 검색 타입 (담당자, 임대인)
    private String keyword;     // 검색 키워드
}
