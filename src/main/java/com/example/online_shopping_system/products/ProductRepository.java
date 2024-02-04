package com.example.online_shopping_system.products;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    Optional<Products> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Products as c
            """)
    List<Products> getAll();
}
