package com.example.online_shopping_system.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Seller as c
            """)
    List<Seller> getAll();
}
