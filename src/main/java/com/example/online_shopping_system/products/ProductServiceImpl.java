package com.example.online_shopping_system.products;

import com.example.online_shopping_system.payment.Payment;
import com.example.online_shopping_system.payment.PaymentDto;
import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService<Integer, ProductDto> {
    private final ProductValidation productValidation;
    private final ProductRepository productRepository;
    private final ProductsMapper productsMapper;

    @Override
    public ApiResponse<ProductDto> create(ProductDto dto) {
        List<ErrorDto> errors = this.productValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<ProductDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Products products = this.productsMapper.toEntity(dto);
        products.setCreatedAt(LocalDateTime.now());
        this.productRepository.save(products);
        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("Ok")
                .data(this.productsMapper.toDto(products))
                .build();
    }

    @Override
    public ApiResponse<ProductDto> get(Integer id) {
        return this.productRepository.findByIdAndDeletedAtIsNull(id)
                .map(products -> ApiResponse.<ProductDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.productsMapper.toDto(products))
                        .build())
                .orElse(ApiResponse.<ProductDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<ProductDto> update(ProductDto dto, Integer id) {
        List<ErrorDto> errors = this.productValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<ProductDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.productRepository.findByIdAndDeletedAtIsNull(id)
                .map(products -> {
                    products.setUpdatedAt(LocalDateTime.now());
                    this.productsMapper.update(products, dto);
                    this.productRepository.save(products);
                    return ApiResponse.<ProductDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.productsMapper.toDto(products))
                            .build();
                })
                .orElse(ApiResponse.<ProductDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<ProductDto> delete(Integer id) {
        return this.productRepository.findByIdAndDeletedAtIsNull(id)
                .map(products -> {
                    products.setDeletedAt(LocalDateTime.now());
                    this.productRepository.delete(products);
                    return ApiResponse.<ProductDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.productsMapper.toDto(products))
                            .build();
                })
                .orElse(ApiResponse.<ProductDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<ProductDto>> getAll() {
        List<Products> products = this.productRepository.getAll();
        if (products.isEmpty()) {
            return ApiResponse.<List<ProductDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<ProductDto>>builder()
                .success(true)
                .message("Ok")
                .data(products.stream().map(this.productsMapper::toDto).toList())
                .build();
    }
}
