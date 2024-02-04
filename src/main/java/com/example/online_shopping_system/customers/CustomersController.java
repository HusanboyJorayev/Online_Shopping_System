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
    @GetMapping("getAll")
    public ApiResponse<List<CustomersDto>> getAll() {
        return this.customersServiceImpl.getAll();
    }
}
