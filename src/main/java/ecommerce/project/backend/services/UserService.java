package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.UserDTO;
import ecommerce.project.backend.entities.User;
import ecommerce.project.backend.requests.LoginRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User saveUser(User user);
    UserDTO loadUser();
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void createUser(UserDTO userDTO);
    String uploadAvatar(Long userId, MultipartFile multipartFile);
    LoginRequest validateLoginRequest(LoginRequest loginRequest);
    void confirmToken(String token);
    void resendConfirmToken(String email);
}
