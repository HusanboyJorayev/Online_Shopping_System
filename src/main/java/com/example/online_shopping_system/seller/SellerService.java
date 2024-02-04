package com.example.online_shopping_system.seller;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService<Integer, SellerDto> {
    ApiResponse<SellerDto> create(SellerDto dto);

    ApiResponse<SellerDto> get(Integer id);

    ApiResponse<SellerDto> update(SellerDto dto, Integer id);

    ApiResponse<SellerDto> delete(Integer id);

    ApiResponse<List<SellerDto>> getAll();
}
