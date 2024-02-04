package com.example.online_shopping_system.deliveries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer> {
    Optional<Deliveries> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Deliveries as c
            """)
    List<Deliveries> getAll();
}
