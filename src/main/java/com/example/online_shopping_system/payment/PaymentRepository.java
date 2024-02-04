package com.example.online_shopping_system.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Optional<Payment> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Payment as c
            """)
    List<Payment> getAll();
}
