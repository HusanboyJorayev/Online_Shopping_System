package com.example.online_shopping_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
@Tag(name = "Online_Shopping_System")
public class OnlineShoppingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingSystemApplication.class, args);
    }

}
