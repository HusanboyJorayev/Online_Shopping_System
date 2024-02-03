package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {

    public List<ErrorDto> validation(CategoryDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or empty", "name"));
        }
        if (StringUtils.isBlank(dto.getType())) {
            errors.add(new ErrorDto("type cannot be null or mepty", "type"));
        }
        return errors;
    }
}
