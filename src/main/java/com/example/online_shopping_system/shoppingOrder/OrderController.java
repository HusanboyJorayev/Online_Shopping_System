package com.example.online_shopping_system.shoppingOrder;

import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController implements OrderService<Integer, OrderDto> {

    private final OrderServiceImpl orderServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<OrderDto> create(@RequestBody @Valid OrderDto dto) {
        return this.orderServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<OrderDto> get(@RequestParam(value = "id") Integer id) {
        return this.orderServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<OrderDto> update(@RequestBody @Valid OrderDto dto, @RequestParam(value = "id") Integer id) {
        return this.orderServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<OrderDto> delete(@RequestParam(value = "id") Integer id) {
        return this.orderServiceImpl.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<OrderDto>> getAll() {
        return this.orderServiceImpl.getAll();
    }
}
