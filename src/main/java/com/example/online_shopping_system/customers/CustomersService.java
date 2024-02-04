package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomersService<Integer, CustomersDto> {

    ApiResponse<CustomersDto> create(CustomersDto dto);

    ApiResponse<CustomersDto> get(Integer id);

    ApiResponse<CustomersDto> update(CustomersDto dto, Integer id);

    ApiResponse<CustomersDto> delete(Integer id);

    ApiResponse<List<CustomersDto>> getAll();
}
