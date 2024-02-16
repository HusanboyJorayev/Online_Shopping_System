package com.example.online_shopping_system.customers;

import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomersController implements CustomersService<Integer, CustomersDto> {
    private final CustomersServiceImpl customersServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<CustomersDto> create(@RequestBody @Valid CustomersDto dto) {
        return this.customersServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<CustomersDto> get(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.get(id);
    }

    @Override
    @GetMapping("/getWithAllTables")
    public ApiResponse<CustomersDto> getWithAllTables(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.getWithAllTables(id);
    }

    @Override
    @GetMapping("/getWithDeliveries")
    public ApiResponse<CustomersDto> getWithDeliveries(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.getWithDeliveries(id);
    }

    @Override
    @GetMapping("/getWithCategories")
    public ApiResponse<CustomersDto> getWithCategories(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.getWithCategories(id);
    }

    @Override
    @GetMapping("/getWithProducts")
    public ApiResponse<CustomersDto> getWithProducts(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.getWithProducts(id);
    }

    @Override
    @GetMapping("/getWithOrders")
    public ApiResponse<CustomersDto> getWithOrders(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.getWithOrders(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<CustomersDto> update(@RequestBody @Valid CustomersDto dto, @RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<CustomersDto> delete(@RequestParam(value = "id") Integer id) {
        return this.customersServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<CustomersDto>> getAll() {
        return this.customersServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getAllCustomersById")
    public ApiResponse<List<CustomersDto>> getAllCustomers(Integer id) {
        return null;
    }
}
