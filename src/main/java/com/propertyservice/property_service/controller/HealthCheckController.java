package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.service.HealthCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name="Health Check")
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @Operation(summary = "healthCheck", description = "애플리케이션 상태 확인 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "애플리케이션이 정상 작동 중",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Application is healthy"))),
            @ApiResponse(responseCode = "500", description = "애플리케이션이 비정상 상태",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Application is not healthy")))
    })
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        if (healthCheckService.checkApplicationHealth()) {
            return ResponseEntity.ok("Application is healthy");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Application is not healthy");
        }
    }
}
