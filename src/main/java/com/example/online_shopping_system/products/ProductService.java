package com.example.online_shopping_system.products;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService<Integer,ProductDto> {
    ApiResponse<ProductDto> create(ProductDto dto);

    ApiResponse<ProductDto> get(Integer id);
    ApiResponse<ProductDto> getWithSeller(Integer id);

    ApiResponse<ProductDto> update(ProductDto dto, Integer id);

    ApiResponse<ProductDto> delete(Integer id);

    ApiResponse<List<ProductDto>> getAll();
}
