package ecommerce.project.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.entities.Post;
import ecommerce.project.backend.enums.Gender;
import ecommerce.project.backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    @NotNull(message = "Full name can't be null!")
    @NotBlank(message = "Full name can't be blank!")
    private String fullName;
    @NotNull(message = "Email can't be null!")
    @NotBlank(message = "Email can't be blank!")
    @Email(message = "Email is invalid!")
    private String email;
    @NotNull(message = "Password can't be null!")
    @NotBlank(message = "Password can't be blank!")
    @Size(min = 6, message = "Password at least 6!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Phone number can't be null!")
    @NotBlank(message = "Phone number can't be blank!")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Phone is invalid!")
    private String phone;
    @NotNull(message = "Day of birth can't be null!")
    private Date dayOfBirth;
    @NotNull(message = "Gender can't be null!")
    private Gender gender;
    private String avatar;
    private Role role;
    private Boolean locked;
    private Boolean enabled;
    private String stripeCustomerId;
}
