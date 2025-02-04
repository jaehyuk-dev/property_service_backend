package com.propertyservice.property_service.dto.office;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeUserSignUpRequest {

    @NotNull
    @NotBlank
    @Schema(description = "소속 중개사무소 코드", example = "CD-1234-1234")
    private String officeCode;

    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Email
    @Schema(description = "이메일", example = "example@email.com")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Schema(description = "비밀번호", example = "password")
    private String password;

    @Schema(description = "핸드폰번호", example = "010-1234-1234")
    private String phoneNumber;
}
