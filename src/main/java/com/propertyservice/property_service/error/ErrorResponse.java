package com.propertyservice.property_service.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    public static ErrorResponse of(String errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    public static ErrorResponse of(String errorCode, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(createErrorMessage(bindingResult))
                .build();
    }

    public static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            if (!sb.isEmpty()) {
                sb.append("; "); // 또는 ", " 사용 가능
            }
            sb.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage());
        }

        //        boolean isFirst = true;

//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            if (isFirst) {
//                sb.append(" ");
//            } else {
//                isFirst = false;
//            }
////            sb.append("[");
//            sb.append(fieldError.getField());
////            sb.append("]");
//            sb.append(": ");
//            sb.append(fieldError.getDefaultMessage());
//            sb.append("\n");
//        }

        return sb.toString();
    }
}
