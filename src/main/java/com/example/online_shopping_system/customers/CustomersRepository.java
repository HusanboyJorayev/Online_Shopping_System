package com.example.online_shopping_system.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    Optional<Customers> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Customers as c
            """)
    List<Customers> getAll();

    List<Customers>findAllByDeletedAtIsNull();
}
