package com.example.online_shopping_system.deliveries;

import com.example.online_shopping_system.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveriesService<Integer, DeliveriesDto> {
    ApiResponse<DeliveriesDto> create(DeliveriesDto dto);

    ApiResponse<DeliveriesDto> get(Integer id);

    ApiResponse<DeliveriesDto> update(DeliveriesDto dto, Integer id);

    ApiResponse<DeliveriesDto> delete(Integer id);

    ApiResponse<List<DeliveriesDto>> getAll();
}
