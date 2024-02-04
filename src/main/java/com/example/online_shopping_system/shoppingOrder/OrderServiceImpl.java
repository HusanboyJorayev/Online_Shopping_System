package com.example.online_shopping_system.shoppingOrder;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService<Integer, OrderDto> {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderValidation orderValidation;

    @Override
    public ApiResponse<OrderDto> create(OrderDto dto) {
        List<ErrorDto> errors = this.orderValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<OrderDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Order seller = this.orderMapper.toEntity(dto);
        seller.setCreatedAt(LocalDateTime.now());
        this.orderRepository.save(seller);
        return ApiResponse.<OrderDto>builder()
                .success(true)
                .message("Ok")
                .data(this.orderMapper.toDto(seller))
                .build();
    }

    @Override
    public ApiResponse<OrderDto> get(Integer id) {
        return this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> ApiResponse.<OrderDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.orderMapper.toDto(seller))
                        .build())
                .orElse(ApiResponse.<OrderDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<OrderDto> update(OrderDto dto, Integer id) {
        List<ErrorDto> errors = this.orderValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<OrderDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> {
                    seller.setUpdatedAt(LocalDateTime.now());
                    this.orderMapper.update(seller, dto);
                    this.orderRepository.save(seller);
                    return ApiResponse.<OrderDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.orderMapper.toDto(seller))
                            .build();
                })
                .orElse(ApiResponse.<OrderDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<OrderDto> delete(Integer id) {
        return this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> {
                    seller.setDeletedAt(LocalDateTime.now());
                    this.orderRepository.delete(seller);
                    return ApiResponse.<OrderDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.orderMapper.toDto(seller))
                            .build();
                })
                .orElse(ApiResponse.<OrderDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<OrderDto>> getAll() {
        List<Order> sellers = this.orderRepository.getAll();
        if (sellers.isEmpty()) {
            return ApiResponse.<List<OrderDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<OrderDto>>builder()
                .success(true)
                .message("Ok")
                .data(sellers.stream().map(this.orderMapper::toDto).toList())
                .build();
    }
}
