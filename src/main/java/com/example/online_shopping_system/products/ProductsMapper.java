package com.example.online_shopping_system.products;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class ProductsMapper {

    public abstract ProductDto toDto(Products products);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Products toEntity(ProductDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Products products, ProductDto dto);
}
