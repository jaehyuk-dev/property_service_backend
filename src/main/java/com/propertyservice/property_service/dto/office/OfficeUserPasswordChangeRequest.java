package com.propertyservice.property_service.dto.office;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeUserPasswordChangeRequest {
    @NotBlank
    @Schema(description = "현재 비밀번호", example = "password")
    private String currentPassword;

    @NotBlank
    @Schema(description = "변경 할 비밀번호", example = "password2")
    private String newPassword;
}
