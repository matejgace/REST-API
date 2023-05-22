package mg.RESTAPI.service;

import mg.RESTAPI.dtos.CategoryDto;


import java.util.List;


public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getcategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);
}
