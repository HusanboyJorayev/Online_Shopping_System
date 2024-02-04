package com.example.online_shopping_system.shoppingOrder;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService <Integer,OrderDto> {

    ApiResponse<OrderDto> create(OrderDto dto);

    ApiResponse<OrderDto> get(Integer id);

    ApiResponse<OrderDto> update(OrderDto dto, Integer id);

    ApiResponse<OrderDto> delete(Integer id);

    ApiResponse<List<OrderDto>> getAll();
}
