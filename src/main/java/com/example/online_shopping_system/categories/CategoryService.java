package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.response.ApiResponse;

import java.util.List;

public interface CategoryService<K, V> {

    ApiResponse<V> create(V dto);

    ApiResponse<V> get(K id);
    ApiResponse<V> getWithProducts(K id);

    ApiResponse<V> update(V dto, K id);

    ApiResponse<V> delete(K id);

    ApiResponse<List<V>> getAll();
}
