package com.example.online_shopping_system.payment;

import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController implements PaymentService<Integer, PaymentDto> {
    private final PaymentServiceImpl paymentServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<PaymentDto> create(@RequestBody @Valid PaymentDto dto) {
        return this.paymentServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<PaymentDto> get(@RequestParam(value = "id") Integer id) {
        return this.paymentServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<PaymentDto> update(@RequestBody @Valid PaymentDto dto, @RequestParam(value = "id") Integer id) {
        return this.paymentServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<PaymentDto> delete(@RequestParam(value = "id") Integer id) {
        return this.paymentServiceImpl.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<PaymentDto>> getAll() {
        return this.paymentServiceImpl.getAll();
    }

}
