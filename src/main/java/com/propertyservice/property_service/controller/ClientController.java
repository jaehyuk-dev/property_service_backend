package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.dto.client.*;
import com.propertyservice.property_service.dto.common.ApiResponseDto;
import com.propertyservice.property_service.dto.common.SuccessResponseDto;
import com.propertyservice.property_service.service.ClientService;
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
@RequestMapping("/api/client")
@Tag(name="Client")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "고객 등록", description = "고객을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<String>> registerClient(@Validated @RequestBody ClientRegisterRequest request) {
        clientService.registerClient(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "고객 목록", description = "고객 목록을 조죄합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<List<ClientSummaryResponse>>> searchClientSummaryList(ClientSearchCondition condition) {
        return ResponseEntity.ok(new SuccessResponseDto<>(clientService.searchClientSummaryList(condition)));
    }

    @Operation(summary = "고객 상세 조회", description = "고객 상세 정보를 조죄합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponseDto<ClientDetailResponse>> searchClientDetail(@PathVariable Long clientId) {
        return ResponseEntity.ok(new SuccessResponseDto<>(clientService.searchClientDetail(clientId)));
    }

    @Operation(summary = "고객 특이사항 등록", description = "고객의 특이사항을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/remark")
    public ResponseEntity<ApiResponseDto<String>> createClientRemark(@Validated @RequestBody ClientRemarkRequest request) {
        clientService.createClientRemark(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "고객 특이사항 삭제", description = "고객의 특이사항을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/remark/{clientRemarkId}")
    public ResponseEntity<ApiResponseDto<String>> removeClientRemark(@PathVariable Long clientRemarkId) {
        clientService.removeClientRemark(clientRemarkId);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "고객 보여줄 매물 등록", description = "고객의 보여줄 매물을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/showing-property")
    public ResponseEntity<ApiResponseDto<String>> createShowingProperty(@Validated @RequestBody ShowingPropertyRegisterRequest request) {
        clientService.createShowingProperty(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "고객 보여줄 매물 삭제", description = "고객의 보여줄 매물 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/showing-property/{showingPropertyId}")
    public ResponseEntity<ApiResponseDto<String>> removeShowingProperty(@PathVariable Long showingPropertyId) {
        clientService.removeShowingProperty(showingPropertyId);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    // todo 고객 상태 변경 api

    // todo 고객 정보 수정 api
}
