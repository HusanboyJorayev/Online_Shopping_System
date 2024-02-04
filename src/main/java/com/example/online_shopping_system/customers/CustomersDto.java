package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.categories.Category;
import com.example.online_shopping_system.categories.CategoryDto;
import com.example.online_shopping_system.deliveries.Deliveries;
import com.example.online_shopping_system.deliveries.DeliveriesDto;
import com.example.online_shopping_system.products.ProductDto;
import com.example.online_shopping_system.products.Products;
import com.example.online_shopping_system.shoppingOrder.Order;
import com.example.online_shopping_system.shoppingOrder.OrderDto;
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
public class CustomersDto {
    private Integer id;
    @NotBlank(message = "name cannot be null or mepty")
    private String name;
    private boolean contactAdd;
    private String address;

    private List<DeliveriesDto> deliveries;
    private List<OrderDto> orders;
    private List<ProductDto> products;
    private List<CategoryDto> categories;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
