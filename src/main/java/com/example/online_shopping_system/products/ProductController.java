package com.example.online_shopping_system.products;

import com.example.online_shopping_system.payment.PaymentDto;
import com.example.online_shopping_system.payment.PaymentService;
import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController implements ProductService<Integer, ProductDto> {
    private final ProductServiceImpl productServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<ProductDto> create(@RequestBody @Valid ProductDto dto) {
        return this.productServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<ProductDto> get(@RequestParam(value = "id") Integer id) {
        return this.productServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<ProductDto> update(@RequestBody @Valid ProductDto dto, @RequestParam(value = "id") Integer id) {
        return this.productServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<ProductDto> delete(@RequestParam(value = "id") Integer id) {
        return this.productServiceImpl.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<ProductDto>> getAll() {
        return this.productServiceImpl.getAll();
    }
}
