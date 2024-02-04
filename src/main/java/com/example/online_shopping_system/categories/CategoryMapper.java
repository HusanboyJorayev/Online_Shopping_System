package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.products.ProductsMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class CategoryMapper {

    @Autowired
    protected ProductsMapper productsMapper;

    public abstract CategoryDto toDto(Category category);

    @Mapping(target = "products", expression = "java(category.getProducts().stream().map(this.productsMapper::toDto).toList())")
    public abstract CategoryDto toDtoWithProducts(Category category);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "products")
    public abstract Category toEntity(CategoryDto dto);


    @Mapping(ignore = true, target = "products")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Category category, CategoryDto dto);


    public void view(Category category, CategoryDto dto) {
        dto.setProducts(category.getProducts().stream().map(this.productsMapper::toDto).toList());
    }
}
