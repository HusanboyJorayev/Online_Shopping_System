package com.example.online_shopping_system.exception;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionClass {

    @ExceptionHandler
    public ApiResponse<Void> voidApiResponseException(MethodArgumentNotValidException e) {

        return ApiResponse.<Void>builder()
                .code(-3)
                .errors(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> {
                            String message = fieldError.getDefaultMessage();
                            String field = fieldError.getField();
                            return new ErrorDto(message, field);
                        })
                        .toList()
                )
                .build();
    }
}
