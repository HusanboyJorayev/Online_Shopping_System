package com.example.online_shopping_system.payment;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentValidation {
    public List<ErrorDto> validation(PaymentDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getDate())) {
            errors.add(new ErrorDto("date cannot be null or empty", "date"));
        }
        if (dto.getCategoryId() == null) {
            errors.add(new ErrorDto("categoryId cannot be null", "categoryId"));
        }
        return errors;
    }
}
