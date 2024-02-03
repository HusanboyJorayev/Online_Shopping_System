package com.example.online_shopping_system.categories;

import com.example.online_shopping_system.response.ApiResponse;
import com.example.online_shopping_system.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService<Integer, CategoryDto> {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryValidation categoryValidation;

    @Override
    public ApiResponse<CategoryDto> create(CategoryDto dto) {
        List<ErrorDto> errors = this.categoryValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<CategoryDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Category category = this.categoryMapper.toEntity(dto);
        category.setCreatedAt(LocalDateTime.now());
        this.categoryRepository.save(category);
        return ApiResponse.<CategoryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.categoryMapper.toDto(category))
                .build();
    }

    @Override
    public ApiResponse<CategoryDto> get(Integer id) {
        return this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .map(category -> ApiResponse.<CategoryDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build())
                .orElse(ApiResponse.<CategoryDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());

    }

    @Override
    public ApiResponse<CategoryDto> update(CategoryDto dto, Integer id) {
        List<ErrorDto> errors = this.categoryValidation.validation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<CategoryDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        return this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .map(category -> {
                    category.setUpdatedAt(LocalDateTime.now());
                    this.categoryMapper.update(category, dto);
                    this.categoryRepository.save(category);
                    return ApiResponse.<CategoryDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.categoryMapper.toDto(category))
                            .build();
                })
                .orElse(ApiResponse.<CategoryDto>builder()
                        .code(-1)
                        .message("Category is not found")
                        .build());

    }

    @Override
    public ApiResponse<CategoryDto> delete(Integer id) {
        return this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .map(category -> {
                    category.setDeletedAt(LocalDateTime.now());
                    this.categoryRepository.delete(category);
                    return ApiResponse.<CategoryDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.categoryMapper.toDto(category))
                            .build();
                })
                .orElse(ApiResponse.<CategoryDto>builder()
                        .code(-1)
                        .message("category is not found")
                        .build());

    }

    @Override
    public ApiResponse<List<CategoryDto>> getAll() {
        List<Category> categories = this.categoryRepository.getAll();
        if (categories.isEmpty()) {
            return ApiResponse.<List<CategoryDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ApiResponse.<List<CategoryDto>>builder()
                .success(true)
                .message("Ok")
                .data(categories.stream().map(this.categoryMapper::toDto).toList())
                .build();
    }
}
