package com.propertyservice.property_service.dto.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertySearchCondition {
    private String searchType;  // 검색 타입 (담당자, 임대인) (매물 주소, 임대인, 임대인 전화번호)
    private String keyword;     // 검색 키워드
}
