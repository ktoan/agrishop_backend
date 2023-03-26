package ecommerce.project.backend.controller;

import ecommerce.project.backend.dto.ProductDTO;
import ecommerce.project.backend.requests.ProductRequest;
import ecommerce.project.backend.responses.PagingResponse;
import ecommerce.project.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> fetchAllProduct(
            @RequestParam(name = "nameLike", defaultValue = "") String s,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "limit", required = false) Integer limit) {
        if (page == null || limit == null) {
            List<ProductDTO> list = productService.fetchAllProduct(s, sortBy, sortDir);
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("products", list);
            return ResponseEntity.ok(resp);
        } else {
            PagingResponse pagingResponse = productService.fetchProductByPaging(s, limit, page, sortBy, sortDir);
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("data", pagingResponse);
            return ResponseEntity.ok(resp);
        }
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createProduct(@ModelAttribute @Valid ProductRequest productRequest) {
        ProductDTO newProduct = productService.createProduct(productRequest);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("newProduct", newProduct);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
