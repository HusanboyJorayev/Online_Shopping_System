package com.example.online_shopping_system.shoppingOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Order as c
            """)
    List<Order> getAll();
}
