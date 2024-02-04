package com.example.online_shopping_system.payment;

import com.example.online_shopping_system.deliveries.Deliveries;
import com.example.online_shopping_system.deliveries.DeliveriesDto;
import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService<Integer, PaymentDto> {
    private final PaymentRepository paymentRepository;
    private final PaymentValidation paymentValidation;
    private final PaymentMapper paymentMapper;

    @Override
    public ApiResponse<PaymentDto> create(PaymentDto dto) {
        List<ErrorDto> errors = this.paymentValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<PaymentDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Payment payment = this.paymentMapper.toEntity(dto);
        payment.setCreatedAt(LocalDateTime.now());
        this.paymentRepository.save(payment);
        return ApiResponse.<PaymentDto>builder()
                .success(true)
                .message("Ok")
                .data(this.paymentMapper.toDto(payment))
                .build();
    }

    @Override
    public ApiResponse<PaymentDto> get(Integer id) {
        return this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .map(payment -> ApiResponse.<PaymentDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.paymentMapper.toDto(payment))
                        .build())
                .orElse(ApiResponse.<PaymentDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<PaymentDto> update(PaymentDto dto, Integer id) {
        List<ErrorDto> errors = this.paymentValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<PaymentDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .map(payment -> {
                    payment.setUpdatedAt(LocalDateTime.now());
                    this.paymentMapper.update(payment, dto);
                    this.paymentRepository.save(payment);
                    return ApiResponse.<PaymentDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.paymentMapper.toDto(payment))
                            .build();
                })
                .orElse(ApiResponse.<PaymentDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());
    }

    @Override
    public ApiResponse<PaymentDto> delete(Integer id) {
        return this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .map(payment -> {
                    payment.setDeletedAt(LocalDateTime.now());
                    this.paymentRepository.delete(payment);
                    return ApiResponse.<PaymentDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.paymentMapper.toDto(payment))
                            .build();
                })
                .orElse(ApiResponse.<PaymentDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<PaymentDto>> getAll() {
        List<Payment> payments = this.paymentRepository.getAll();
        if (payments.isEmpty()) {
            return ApiResponse.<List<PaymentDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<PaymentDto>>builder()
                .success(true)
                .message("Ok")
                .data(payments.stream().map(this.paymentMapper::toDto).toList())
                .build();
    }
}
