package com.propertyservice.property_service.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 공통 에러
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "40001", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "40101", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "40301", "권한이 없습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "40401", "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "40501", "지원하지 않는 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "50001", "서버 내부 오류가 발생했습니다."),

    // 사용자 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "40402", "사용자를 찾을 수 없습니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT, "40901", "중복된 사용자입니다."),
    INVALID_USER_CREDENTIALS(HttpStatus.UNAUTHORIZED, "40102", "사용자 인증 정보가 올바르지 않습니다."),

    // 비즈니스 로직 관련 에러
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "40403", "주문을 찾을 수 없습니다."),
    PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "40002", "결제가 실패했습니다."),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "40003", "잔액이 부족합니다."),
    PRODUCT_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "40004", "상품 재고가 부족합니다."),

    // 데이터 관련 에러
    DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "40902", "데이터 무결성 위반이 발생했습니다."),
    INVALID_DATA_FORMAT(HttpStatus.BAD_REQUEST, "40005", "데이터 형식이 유효하지 않습니다."),
    DUPLICATE_ENTRY(HttpStatus.CONFLICT, "40903", "중복된 데이터가 존재합니다.");

    ;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
