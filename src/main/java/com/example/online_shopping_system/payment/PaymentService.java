package com.example.online_shopping_system.payment;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService<Integer,PaymentDto>{
    ApiResponse<PaymentDto> create(PaymentDto dto);

    ApiResponse<PaymentDto> get(Integer id);

    ApiResponse<PaymentDto> update(PaymentDto dto, Integer id);

    ApiResponse<PaymentDto> delete(Integer id);

    ApiResponse<List<PaymentDto>> getAll();
}
