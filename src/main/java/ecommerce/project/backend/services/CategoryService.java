package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.CategoryDTO;
import ecommerce.project.backend.entities.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategoryByCode(String code);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    List<CategoryDTO> fetchAllCategories();
}
