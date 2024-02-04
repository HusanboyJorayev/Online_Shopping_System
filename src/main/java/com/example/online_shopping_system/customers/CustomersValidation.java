package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomersValidation {

    public List<ErrorDto> validation(CustomersDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or mepty", "name"));
        }
        return errors;
    }
}
