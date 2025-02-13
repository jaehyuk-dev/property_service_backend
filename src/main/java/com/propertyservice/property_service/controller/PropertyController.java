package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.domain.property.Building;
import com.propertyservice.property_service.dto.client.ClientDetailResponse;
import com.propertyservice.property_service.dto.common.ApiResponseDto;
import com.propertyservice.property_service.dto.common.SuccessResponseDto;
import com.propertyservice.property_service.dto.property.*;
import com.propertyservice.property_service.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
@Tag(name="Property")
public class PropertyController {
    private final PropertyService propertyService;

    @Operation(summary = "매물 요약 목록 조회", description = "매물 요약 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/summary-list")
    public ResponseEntity<ApiResponseDto<List<PropertySummaryResponse>>> searchClientDetail(PropertySearchCondition condition) {
        return ResponseEntity.ok(new SuccessResponseDto<>(propertyService.searchPropertySummaryList(condition)));
    }

    @Operation(summary = "건물 등록", description = "건물을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/building")
    public ResponseEntity<ApiResponseDto<String>> registerBuilding(@Validated @RequestBody BuildingRegisterRequest request) {
        propertyService.registerBuilding(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "건물 수정", description = "건물을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/building")
    public ResponseEntity<ApiResponseDto<String>> updateBuilding(@Validated @RequestBody BuildingUpdateRequest request) {
        propertyService.updateBuilding(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "건물 요약 목록 조회", description = "건물 요약 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/building/summary-list")
    public ResponseEntity<ApiResponseDto<List<BuildingSummaryResponse>>> searchClientDetail(BuildingSearchCondition condition) {
        return ResponseEntity.ok(new SuccessResponseDto<>(propertyService.searchBuildingSummaryList(condition)));
    }

    @Operation(summary = "매물 등록", description = "매물을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<String>> registerProperty(@Validated @RequestBody PropertyRegisterRequest request) {
        propertyService.registerProperty(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

}
