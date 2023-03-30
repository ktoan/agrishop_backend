package ecommerce.project.backend.dto;

import ecommerce.project.backend.entities.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO extends BaseDTO {
    private String title;
    private String shortDescription;
    private String content;
    private Image image;
    private UserDTO author;
}
