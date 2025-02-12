package com.propertyservice.property_service.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientSearchCondition {
    private String searchType;  // 검색 타입 (담당자, 고객, 고객 전화번호)
    private String keyword;     // 검색 키워드
}
