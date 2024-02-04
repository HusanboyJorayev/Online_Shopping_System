package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.products.ProductDto;
import com.example.online_shopping_system.products.Products;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Integer id;
    private Integer customerId;
    @NotBlank(message = "name cannot be nul or empty")
    private String name;
    @NotBlank(message = "name cannot be nul or empty")
    private String type;

    private List<ProductDto> products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
