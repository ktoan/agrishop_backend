package ecommerce.project.backend.dto;

import ecommerce.project.backend.entities.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductDTO extends BaseDTO {
    private String name;
    private String shortDescription;
    private String information;
    private Long amount;
    private Set<Image> images = new HashSet<>();
    private Double saleOff;
    private Set<CategoryDTO> categories = new HashSet<>();
    private Double price;
}
