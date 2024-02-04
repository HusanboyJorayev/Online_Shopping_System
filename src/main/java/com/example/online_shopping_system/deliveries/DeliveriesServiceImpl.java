package com.example.online_shopping_system.deliveries;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveriesServiceImpl implements DeliveriesService<Integer, DeliveriesDto> {
    private final DeliveriesRepository deliveriesRepository;
    private final DeliveriesValidation deliveriesValidation;
    private final DeliveriesMapper deliveriesMapper;

    @Override
    public ApiResponse<DeliveriesDto> create(DeliveriesDto dto) {
        List<ErrorDto> errors = this.deliveriesValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<DeliveriesDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Deliveries deliveries = this.deliveriesMapper.toEntity(dto);
        deliveries.setCreatedAt(LocalDateTime.now());
        this.deliveriesRepository.save(deliveries);
        return ApiResponse.<DeliveriesDto>builder()
                .success(true)
                .message("Ok")
                .data(this.deliveriesMapper.toDto(deliveries))
                .build();
    }

    @Override
    public ApiResponse<DeliveriesDto> get(Integer id) {
        return this.deliveriesRepository.findByIdAndDeletedAtIsNull(id)
                .map(deliveries -> ApiResponse.<DeliveriesDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.deliveriesMapper.toDto(deliveries))
                        .build())
                .orElse(ApiResponse.<DeliveriesDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<DeliveriesDto> update(DeliveriesDto dto, Integer id) {
        List<ErrorDto> errors = this.deliveriesValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<DeliveriesDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.deliveriesRepository.findByIdAndDeletedAtIsNull(id)
                .map(deliveries -> {
                    deliveries.setUpdatedAt(LocalDateTime.now());
                    this.deliveriesMapper.update(deliveries, dto);
                    this.deliveriesRepository.save(deliveries);
                    return ApiResponse.<DeliveriesDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.deliveriesMapper.toDto(deliveries))
                            .build();
                })
                .orElse(ApiResponse.<DeliveriesDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<DeliveriesDto> delete(Integer id) {
        return this.deliveriesRepository.findByIdAndDeletedAtIsNull(id)
                .map(deliveries -> {
                    deliveries.setDeletedAt(LocalDateTime.now());
                    this.deliveriesRepository.delete(deliveries);
                    return ApiResponse.<DeliveriesDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.deliveriesMapper.toDto(deliveries))
                            .build();
                })
                .orElse(ApiResponse.<DeliveriesDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<DeliveriesDto>> getAll() {
        List<Deliveries> deliveries = this.deliveriesRepository.getAll();
        if (deliveries.isEmpty()) {
            return ApiResponse.<List<DeliveriesDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<DeliveriesDto>>builder()
                .success(true)
                .message("Ok")
                .data(deliveries.stream().map(this.deliveriesMapper::toDto).toList())
                .build();
    }
}
