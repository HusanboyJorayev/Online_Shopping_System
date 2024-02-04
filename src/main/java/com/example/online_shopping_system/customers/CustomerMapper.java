package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.categories.CategoryMapper;
import com.example.online_shopping_system.deliveries.DeliveriesMapper;
import com.example.online_shopping_system.products.ProductsMapper;
import com.example.online_shopping_system.shoppingOrder.OrderMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class CustomerMapper {

    @Autowired
    protected DeliveriesMapper deliveriesMapper;
    @Autowired
    protected OrderMapper orderMapper;
    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected ProductsMapper productsMapper;


    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "products")
    @Mapping(ignore = true, target = "categories")
    @Mapping(ignore = true, target = "deliveries")
    public abstract CustomersDto toDto(Customers customers);


    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "products")
    @Mapping(ignore = true, target = "categories")
    @Mapping(target = "deliveries", expression = "java(customers.getDeliveries().stream().map(this.deliveriesMapper::toDto).toList())")
    public abstract CustomersDto toDtoWithDeliveries(Customers customers);


    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "products")
    @Mapping(target = "categories", expression = "java(customers.getCategories().stream().map(this.categoryMapper::toDto).toList())")
    @Mapping(ignore = true, target = "deliveries")
    public abstract CustomersDto toDtoWithCategories(Customers customers);


    @Mapping(target = "orders", expression = "java(customers.getOrders().stream().map(this.orderMapper::toDto).toList())")
    @Mapping(ignore = true, target = "products")
    @Mapping(ignore = true, target = "categories")
    @Mapping(ignore = true, target = "deliveries")
    public abstract CustomersDto toDtoWithOrder(Customers customers);


    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "categories")
    @Mapping(ignore = true, target = "deliveries")
    @Mapping(target = "products", expression = "java(customers.getProducts().stream().map(this.productsMapper::toDto).toList())")
    public abstract CustomersDto toDtoWithProduct(Customers customers);


    @Mapping(target = "orders", expression = "java(customers.getOrders().stream().map(this.orderMapper::toDto).toList())")
    @Mapping(target = "products", expression = "java(customers.getProducts().stream().map(this.productsMapper::toDto).toList())")
    @Mapping(target = "categories", expression = "java(customers.getCategories().stream().map(this.categoryMapper::toDto).toList())")
    @Mapping(target = "deliveries", expression = "java(customers.getDeliveries().stream().map(this.deliveriesMapper::toDto).toList())")
    public abstract CustomersDto toDtoWithAllTables(Customers customers);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "deliveries")
    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "products")
    @Mapping(ignore = true, target = "categories")
    public abstract Customers toEntity(CustomersDto dto);


    @Mapping(ignore = true, target = "orders")
    @Mapping(ignore = true, target = "products")
    @Mapping(ignore = true, target = "categories")
    @Mapping(ignore = true, target = "deliveries")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Customers customers, CustomersDto dto);

    public void view(Customers customers, CustomersDto dto) {
        dto.setCategories(customers.getCategories().stream().map(this.categoryMapper::toDto).toList());
        dto.setDeliveries(customers.getDeliveries().stream().map(this.deliveriesMapper::toDto).toList());
        dto.setOrders(customers.getOrders().stream().map(this.orderMapper::toDto).toList());
        dto.setProducts(customers.getProducts().stream().map(this.productsMapper::toDto).toList());
    }
}
