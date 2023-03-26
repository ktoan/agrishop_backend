package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.ProductDTO;
import ecommerce.project.backend.entities.Product;
import ecommerce.project.backend.requests.ProductRequest;
import ecommerce.project.backend.responses.PagingResponse;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    ProductDTO createProduct(ProductRequest productRequest);
    PagingResponse fetchProductByPaging(String s, Integer limit, Integer page, String sortBy, String sortDir);
    List<ProductDTO> fetchAllProduct(String s, String sortBy, String sortDir);
}
