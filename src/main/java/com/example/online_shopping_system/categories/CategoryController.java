package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController implements CategoryService<Integer, CategoryDto> {
    private final CategoryServiceImpl categoryService;

    @Override
    @PostMapping("/create")
    public ApiResponse<CategoryDto> create(@RequestBody @Valid CategoryDto dto) {
        return this.categoryService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ApiResponse<CategoryDto> get(@RequestParam(value = "id") Integer id) {
        return this.categoryService.get(id);
    }

    @Override
    @GetMapping("/getWithProduct")
    public ApiResponse<CategoryDto> getWithProducts(@RequestParam(value = "id") Integer id) {
        return this.categoryService.getWithProducts(id);
    }

    @Override
    @PutMapping("/update")
    public ApiResponse<CategoryDto> update(@RequestBody @Valid CategoryDto dto, @RequestParam(value = "id") Integer id) {
        return this.categoryService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ApiResponse<CategoryDto> delete(@RequestParam(value = "id") Integer id) {
        return this.categoryService.delete(id);
    }

    @Override
    @GetMapping("getAll")
    public ApiResponse<List<CategoryDto>> getAll() {
        return this.categoryService.getAll();
    }
}
