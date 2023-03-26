package ecommerce.project.backend.controller;

import ecommerce.project.backend.dto.CategoryDTO;
import ecommerce.project.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Object> fetchAllCategories() {
        List<CategoryDTO> list = categoryService.fetchAllCategories();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("categories", list);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@ModelAttribute @Valid CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.createCategory(categoryDTO);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("newCategory", newCategory);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
