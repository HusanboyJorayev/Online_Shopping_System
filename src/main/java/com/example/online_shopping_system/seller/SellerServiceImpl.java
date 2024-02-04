package com.example.online_shopping_system.seller;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService<Integer, SellerDto> {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final SellerValidation sellerValidation;

    @Override
    public ApiResponse<SellerDto> create(SellerDto dto) {
        List<ErrorDto> errors = this.sellerValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<SellerDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Seller seller = this.sellerMapper.toEntity(dto);
        seller.setCreatedAt(LocalDateTime.now());
        this.sellerRepository.save(seller);
        return ApiResponse.<SellerDto>builder()
                .success(true)
                .message("Ok")
                .data(this.sellerMapper.toDto(seller))
                .build();
    }

    @Override
    public ApiResponse<SellerDto> get(Integer id) {
        return this.sellerRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> ApiResponse.<SellerDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.sellerMapper.toDto(seller))
                        .build())
                .orElse(ApiResponse.<SellerDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<SellerDto> update(SellerDto dto, Integer id) {
        List<ErrorDto> errors = this.sellerValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<SellerDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.sellerRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> {
                    seller.setUpdatedAt(LocalDateTime.now());
                    this.sellerMapper.update(seller, dto);
                    this.sellerRepository.save(seller);
                    return ApiResponse.<SellerDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.sellerMapper.toDto(seller))
                            .build();
                })
                .orElse(ApiResponse.<SellerDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<SellerDto> delete(Integer id) {
        return this.sellerRepository.findByIdAndDeletedAtIsNull(id)
                .map(seller -> {
                    seller.setDeletedAt(LocalDateTime.now());
                    this.sellerRepository.delete(seller);
                    return ApiResponse.<SellerDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.sellerMapper.toDto(seller))
                            .build();
                })
                .orElse(ApiResponse.<SellerDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<SellerDto>> getAll() {
        List<Seller> sellers = this.sellerRepository.getAll();
        if (sellers.isEmpty()) {
            return ApiResponse.<List<SellerDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<SellerDto>>builder()
                .success(true)
                .message("Ok")
                .data(sellers.stream().map(this.sellerMapper::toDto).toList())
                .build();
    }
}
