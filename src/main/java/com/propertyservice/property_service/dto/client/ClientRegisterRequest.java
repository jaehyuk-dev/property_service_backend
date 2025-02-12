package com.propertyservice.property_service.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Getter
@Service
public class ClientRegisterRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    private int genderCode;
    @NotNull
    @NotBlank
    private String source;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    private LocalDate moveInDate;

    @NotNull
    private List<Integer> expectedTransactionTypeCodeList;

    private String remark;
}
