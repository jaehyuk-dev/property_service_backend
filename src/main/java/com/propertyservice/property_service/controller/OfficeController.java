package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.domain.office.Office;
import com.propertyservice.property_service.dto.common.ApiResponseDto;
import com.propertyservice.property_service.dto.common.SuccessResponseDto;
import com.propertyservice.property_service.dto.office.OfficeRegisterRequest;
import com.propertyservice.property_service.dto.office.OfficeRegisterResponse;
import com.propertyservice.property_service.dto.office.OfficeSearchRequest;
import com.propertyservice.property_service.dto.office.OfficeSearchResponse;
import com.propertyservice.property_service.service.HealthCheckService;
import com.propertyservice.property_service.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offices")
@Tag(name="Office")
public class OfficeController {

    private final OfficeService officeService;

    @Operation(summary = "중개 사무소 등록", description = "중개 사무소를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록된 중개사무소 코드",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/office")
    public ResponseEntity<ApiResponseDto<OfficeRegisterResponse>> registerOffice(@Validated @RequestBody OfficeRegisterRequest request) {
        return ResponseEntity.ok(new SuccessResponseDto<>(
                officeService.registerOffice(request)
        ));
    }

    @Operation(summary = "중개 사무소 조회", description = "중개 사무소를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회된 중개 사무소 정보",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/office")
    public ResponseEntity<ApiResponseDto<OfficeSearchResponse>> searchOffice(@Validated OfficeSearchRequest request) {
        return ResponseEntity.ok(new SuccessResponseDto<>(
                officeService.searchOffice(request)
        ));
    }
}
