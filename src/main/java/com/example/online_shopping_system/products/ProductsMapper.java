package com.example.online_shopping_system.products;

import com.example.online_shopping_system.seller.SellerMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class ProductsMapper {

    @Autowired
    protected SellerMapper sellerMapper;

    @Mapping(ignore = true, target = "sellers")
    public abstract ProductDto toDto(Products products);


    @Mapping(target = "sellers", expression = "java(products.getSellers().stream().map(this.sellerMapper::toDto).toList())")
    public abstract ProductDto toDtoWithSeller(Products products);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "sellers")
    public abstract Products toEntity(ProductDto dto);


    @Mapping(ignore = true, target = "sellers")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Products products, ProductDto dto);

    public void view(Products products, ProductDto dto) {
        dto.setSellers(products.getSellers().stream().map(this.sellerMapper::toDto).toList());
    }
}
