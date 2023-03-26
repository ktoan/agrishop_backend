package ecommerce.project.backend.services.impl;

import ecommerce.project.backend.dto.CategoryDTO;
import ecommerce.project.backend.entities.Category;
import ecommerce.project.backend.exceptions.BadRequestException;
import ecommerce.project.backend.exceptions.NotFoundException;
import ecommerce.project.backend.mappers.CategoryMapper;
import ecommerce.project.backend.repositories.CategoryRepository;
import ecommerce.project.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static ecommerce.project.backend.constants.Messaging.CATEGORY_NOT_FOUND_CODE_MSG;
import static ecommerce.project.backend.constants.Messaging.CATEGORY_NOT_FOUND_ID_MSG;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByCode(String code) {
        return categoryRepository.findByCode(code).orElseThrow(() -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_CODE_MSG, code)));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByCode(categoryDTO.getCode())) {
            throw new BadRequestException("Code is already taken!");
        }
        Category category = new Category();
        category.setCode(categoryDTO.getCode());
        category.setName(categoryDTO.getName());
        category = saveCategory(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_ID_MSG, categoryId)));
        category.setCode(categoryDTO.getCode());
        category.setName(categoryDTO.getName());
        category = saveCategory(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> fetchAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }
}
