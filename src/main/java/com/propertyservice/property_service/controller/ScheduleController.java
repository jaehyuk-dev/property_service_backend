package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.dto.common.ApiResponseDto;
import com.propertyservice.property_service.dto.common.SuccessResponseDto;
import com.propertyservice.property_service.dto.schedule.ScheduleCompleteRequest;
import com.propertyservice.property_service.dto.schedule.ScheduleRegisterRequest;
import com.propertyservice.property_service.service.ScheduleService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@Tag(name="Schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "일정 등록", description = "일정을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<String>> createSchedule(@Validated @RequestBody ScheduleRegisterRequest request) {
        scheduleService.createSchedule(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "일정 상태 변경", description = "일정 상태를 변경 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/")
    public ResponseEntity<ApiResponseDto<String>> updateSchedule(@Validated @RequestBody ScheduleCompleteRequest request) {
        scheduleService.updateSchedule(request);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }

    @Operation(summary = "일정 삭제", description = "일정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Checked Error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Uncheck Error",
                    content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<String>> removeSchedule(@PathVariable Long scheduleId) {
        scheduleService.removeSchedule(scheduleId);
        return ResponseEntity.ok(new SuccessResponseDto<>("success"));
    }


}
