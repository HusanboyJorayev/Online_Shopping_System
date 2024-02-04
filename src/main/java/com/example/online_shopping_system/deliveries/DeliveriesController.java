package com.example.online_shopping_system.deliveries;

import com.example.online_shopping_system.customers.CustomersDto;
import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("deliveries")
public class DeliveriesController implements DeliveriesService<Integer,DeliveriesDto>{
    private final DeliveriesServiceImpl deliveriesServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<DeliveriesDto> create(@RequestBody @Valid DeliveriesDto dto) {
        return this.deliveriesServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<DeliveriesDto> get(@RequestParam(value = "id") Integer id) {
        return this.deliveriesServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<DeliveriesDto> update(@RequestBody @Valid DeliveriesDto dto, @RequestParam(value = "id") Integer id) {
        return this.deliveriesServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<DeliveriesDto> delete(@RequestParam(value = "id") Integer id) {
        return this.deliveriesServiceImpl.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<DeliveriesDto>> getAll() {
        return this.deliveriesServiceImpl.getAll();
    }
}
