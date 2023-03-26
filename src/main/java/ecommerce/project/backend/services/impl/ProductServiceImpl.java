package ecommerce.project.backend.services.impl;

import ecommerce.project.backend.dto.ProductDTO;
import ecommerce.project.backend.entities.Category;
import ecommerce.project.backend.entities.Image;
import ecommerce.project.backend.entities.Product;
import ecommerce.project.backend.mappers.ProductMapper;
import ecommerce.project.backend.repositories.ProductRepository;
import ecommerce.project.backend.requests.ProductRequest;
import ecommerce.project.backend.responses.PagingResponse;
import ecommerce.project.backend.services.CategoryService;
import ecommerce.project.backend.services.ImageService;
import ecommerce.project.backend.services.ProductService;
import ecommerce.project.backend.utils.paging.SortUtils;
import ecommerce.project.backend.utils.upload.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final FileService fileService;
    private final ImageService imageService;
    private final SortUtils sortUtils;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductDTO createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setShortDescription(productRequest.getShortDescription());
        product.setInformation(productRequest.getInformation());
        product.setAmount(productRequest.getAmount());
        product.setSaleOff(0.0);
        for (MultipartFile file : productRequest.getImages()) {
            String url = fileService.uploadImage(file);
            Image image = new Image(url);
            image = imageService.saveImage(image);
            product.addImage(image);
        }
        for (String categoryCode : productRequest.getCategoryListCode()) {
            Category category = categoryService.getCategoryByCode(categoryCode);
            product.addCategory(category);
        }
        product.setPrice(productRequest.getPrice());
        product = saveProduct(product);
        return productMapper.toDTO(product);
    }

    @Override
    public PagingResponse fetchProductByPaging(String s, Integer limit, Integer page, String sortBy, String sortDir) {
        Sort sort = sortUtils.getSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Product> productPage = productRepository.findAllByNameContaining(s, pageable);
        return new PagingResponse(productPage.getContent(), productPage.getNumber(), productPage.getSize(), productPage.getTotalPages(), productPage.getTotalElements());
    }

    @Override
    public List<ProductDTO> fetchAllProduct(String s, String sortBy, String sortDir) {
        Sort sort = sortUtils.getSort(sortBy, sortDir);
        List<Product> products = productRepository.findAllByNameContaining(s, sort);
        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }
}
