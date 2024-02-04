package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomersServiceImpl implements CustomersService<Integer, CustomersDto> {
    private final CustomersRepository customersRepository;
    private final CustomerMapper customerMapper;
    private final CustomersValidation customersValidation;

    @Override
    public ApiResponse<CustomersDto> create(CustomersDto customersDto) {
        List<ErrorDto> errors = this.customersValidation.validation(customersDto);
        if (!errors.isEmpty()) {
            return ApiResponse.<CustomersDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Customers customers = this.customerMapper.toEntity(customersDto);
        customers.setCreatedAt(LocalDateTime.now());
        this.customersRepository.save(customers);
        return ApiResponse.<CustomersDto>builder()
                .success(true)
                .message("Ok")
                .data(this.customerMapper.toDto(customers))
                .build();
    }

    @Override
    public ApiResponse<CustomersDto> get(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(category -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDto(category))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> update(CustomersDto customersDto, Integer id) {
        List<ErrorDto> errors = this.customersValidation.validation(customersDto);
        if (!errors.isEmpty()) {
            return ApiResponse.<CustomersDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> {
                    customers.setUpdatedAt(LocalDateTime.now());
                    this.customerMapper.update(customers, customersDto);
                    this.customersRepository.save(customers);
                    return ApiResponse.<CustomersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.customerMapper.toDto(customers))
                            .build();
                })
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> delete(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> {
                    customers.setDeletedAt(LocalDateTime.now());
                    this.customersRepository.delete(customers);
                    return ApiResponse.<CustomersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.customerMapper.toDto(customers))
                            .build();
                })
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<CustomersDto>> getAll() {
        List<Customers> categories = this.customersRepository.getAll();
        if (categories.isEmpty()) {
            return ApiResponse.<List<CustomersDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<CustomersDto>>builder()
                .success(true)
                .message("Ok")
                .data(categories.stream().map(this.customerMapper::toDto).toList())
                .build();
    }
}
