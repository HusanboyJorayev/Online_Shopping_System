package com.example.online_shopping_system.payment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Integer id;
    @NotNull(message = "categoryId cannot be null")
    private Integer customerId;
    @NotBlank(message = "date cannot be null or empty")
    private String date;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
