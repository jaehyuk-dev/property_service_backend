package com.propertyservice.property_service.controller;

import com.propertyservice.property_service.domain.office.Office;
import com.propertyservice.property_service.dto.office.OfficeRegisterRequest;
import com.propertyservice.property_service.service.HealthCheckService;
import com.propertyservice.property_service.service.OfficeService;
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
import org.springframework.validation.BindingResult;
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
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "CD-1234-1234"))),
//            @ApiResponse(responseCode = "500", description = "애플리케이션이 비정상 상태",
//                    content = @Content(mediaType = "text/plain",
//                            schema = @Schema(implementation = String.class),
//                            examples = @ExampleObject(value = "Application is not healthy")))
    })
    @PostMapping("/office")
    public ResponseEntity<String> registerOffice(@RequestBody OfficeRegisterRequest request, BindingResult bindingResult) {
       return ResponseEntity.ok(
               officeService.registerOffice(request)
       );
    }
}
