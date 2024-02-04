package com.example.online_shopping_system.seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {
    private Integer id;
    @NotNull(message = "productId cannot be null")
    private Integer productId;
    @NotBlank(message = "name cannot be null or empty")
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
