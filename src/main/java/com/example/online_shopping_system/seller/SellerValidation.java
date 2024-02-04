package com.example.online_shopping_system.seller;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellerValidation {
    public List<ErrorDto> validation(SellerDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or empty", "name"));
        }
        if (dto.getProductId() == null) {
            errors.add(new ErrorDto("productId cannot be null", "productId"));
        }
        return errors;
    }
}
