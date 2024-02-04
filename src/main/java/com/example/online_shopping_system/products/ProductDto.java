package com.example.online_shopping_system.products;

import com.example.online_shopping_system.seller.Seller;
import com.example.online_shopping_system.seller.SellerDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer id;
    private Integer customerId;
    @NotNull(message = "categoryId cannot be null")
    private Integer categoryId;
    @NotBlank(message = "name cannot be null or mepty")
    private String name;

    private List<SellerDto> sellers;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
