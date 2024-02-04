package com.example.online_shopping_system.apiSwagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApi {

    @Bean
    public GroupedOpenApi apiCategory() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/category/**")
                .group("Category")
                .build();
    }

    @Bean
    public GroupedOpenApi apiCustomer() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/customers/**")
                .group("Customers")
                .build();
    }

    @Bean
    public GroupedOpenApi apiDelivery() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/deliveries/**")
                .group("Delivery")
                .build();
    }

    @Bean
    public GroupedOpenApi apiPayment() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/payment/**")
                .group("Payment")
                .build();
    }

    @Bean
    public GroupedOpenApi apiProduct() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/product/**")
                .group("Product")
                .build();
    }

    @Bean
    public GroupedOpenApi apiSeller() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/seller/**")
                .group("Seller")
                .build();
    }

    @Bean
    public GroupedOpenApi apiOrder() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/orders/**")
                .group("Orders")
                .build();
    }
}
