package com.example.online_shopping_system.products;

import com.example.online_shopping_system.seller.Seller;
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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer categoryId;
    private Integer customerId;
    private String name;

    @OneToMany(mappedBy = "productId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Seller>sellers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
