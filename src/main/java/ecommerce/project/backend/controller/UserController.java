package ecommerce.project.backend.controller;

import ecommerce.project.backend.dto.UserDTO;
import ecommerce.project.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/change-avatar/{userId}")
    public ResponseEntity<Object> changeAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        String newAvatar = userService.uploadAvatar(userId, file);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("newAvatarURL", newAvatar);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("updatedUser", updatedUser);
        return ResponseEntity.ok(resp);
    }

}
