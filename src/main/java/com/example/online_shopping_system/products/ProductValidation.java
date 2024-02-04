package com.example.online_shopping_system.products;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {
    public List<ErrorDto> validation(ProductDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or empty", "name"));
        }
        if (dto.getCategoryId() == null) {
            errors.add(new ErrorDto("categoryId cannot be null", "categoryId"));
        }
        return errors;
    }
}
