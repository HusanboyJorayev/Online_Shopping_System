package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDto(customers))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> getWithAllTables(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDtoWithAllTables(customers))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> getWithDeliveries(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDtoWithDeliveries(customers))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> getWithCategories(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDtoWithCategories(customers))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> getWithProducts(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDtoWithProduct(customers))
                        .build())
                .orElse(ApiResponse.<CustomersDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<CustomersDto> getWithOrders(Integer id) {
        return this.customersRepository.findByIdAndDeletedAtIsNull(id)
                .map(customers -> ApiResponse.<CustomersDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.customerMapper.toDtoWithOrder(customers))
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

    @Override
    public ApiResponse<List<CustomersDto>> getAllCustomers(Integer id) {
        List<Customers> customers = this.customersRepository.findAllByDeletedAtIsNull();
        List<Customers> customersList = new ArrayList<>();
        for (Customers customer : customers) {
            if (customer.getId().equals(id)) {
                customersList.add(customer);
            }
        }
        return ApiResponse.<List<CustomersDto>>builder()
                .success(true)
                .message("Ok")
                .data(customersList.stream().map(this.customerMapper::toDto).toList())
                .build();
    }
}
