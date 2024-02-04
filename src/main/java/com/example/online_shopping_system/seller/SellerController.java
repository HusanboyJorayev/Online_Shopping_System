package com.example.online_shopping_system.seller;

import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("seller")
public class SellerController implements SellerService<Integer, SellerDto> {
    private final SellerServiceImpl sellerServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<SellerDto> create(@RequestBody @Valid SellerDto dto) {
        return this.sellerServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<SellerDto> get(@RequestParam(value = "id") Integer id) {
        return this.sellerServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<SellerDto> update(@RequestBody @Valid SellerDto dto, @RequestParam(value = "id") Integer id) {
        return this.sellerServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<SellerDto> delete(@RequestParam(value = "id") Integer id) {
        return this.sellerServiceImpl.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<SellerDto>> getAll() {
        return this.sellerServiceImpl.getAll();
    }
}
