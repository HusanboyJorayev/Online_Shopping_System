package com.example.online_shopping_system.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  c from Category as c
            """)
    List<Category> getAll();
}
