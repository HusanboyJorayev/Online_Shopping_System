package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.products.Products;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer customerId;
    private String name;
    private String type;

    @OneToMany(mappedBy = "categoryId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Products>products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
