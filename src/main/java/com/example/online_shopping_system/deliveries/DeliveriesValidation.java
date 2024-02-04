package com.example.online_shopping_system.deliveries;

import com.example.online_shopping_system.response.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliveriesValidation {

    public List<ErrorDto>validation(DeliveriesDto dto){
        List<ErrorDto>errors=new ArrayList<>();
        if (StringUtils.isBlank(dto.getDate())){
            errors.add(new ErrorDto("date cannot be null or mepty","date"));
        }
        if (dto.getCustomerId()==null){
            errors.add(new ErrorDto("customerId cannot be null","customerId"));
        }
        return errors;
    }
}
