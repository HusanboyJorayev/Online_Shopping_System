package com.example.online_shopping_system.shoppingOrder;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderValidation {
    public List<ErrorDto> validation(OrderDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or empty", "name"));
        }
        if (dto.getCustomerId() == null) {
            errors.add(new ErrorDto("customerId cannot be null", "productId"));
        }
        return errors;
    }
}
