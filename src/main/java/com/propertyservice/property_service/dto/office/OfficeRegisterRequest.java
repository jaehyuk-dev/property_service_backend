package com.propertyservice.property_service.dto.office;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class OfficeRegisterRequest {

    @NotNull
    @NotBlank
    @Schema(description = "중개사무소 이름", example = "중개사무소")
    private String officeName;

    @NotNull
    @NotBlank
    @Schema(description = "우편번호", example = "12345")
    private String zoneCode; // 우편번호

    @NotNull
    @NotBlank
    @Schema(description = "중개사무소 주소", example = "서울특별시 강동구 둔촌동 123-12")
    private String officeAddress; // 주소

    @Schema(description = "중개사무소 상세 주소", example = "101동 101호")
    private String addressDetail; // 상세주소

    @Schema(description = "대표자 명", example = "홍길동")
    private String presidentName; // 대표자명

    @NotNull
    @NotBlank
    @Schema(description = "대표자 이메일", example = "example@email.com")
    private String presidentEmail; // 대표자 이메일

    @Schema(description = "대표자 핸드폰 번호", example = "010-1234-1234")
    private String mobileNumber; // 대표자 핸드폰번호

    @Schema(description = "중개사무소 전화번호", example = "02-1234-1234")
    private String phoneNumber; // 전화번호
}
