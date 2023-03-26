package ecommerce.project.backend.controller;

import ecommerce.project.backend.config.jwt.JwtTokenUtils;
import ecommerce.project.backend.dto.UserDTO;
import ecommerce.project.backend.requests.LoginRequest;
import ecommerce.project.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;

    @GetMapping("/loadUser")
    public ResponseEntity<Object> loadUser() {
        UserDTO loadedUser = userService.loadUser();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("loadedUser", loadedUser);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("msg", "Create user successfully!");
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        loginRequest = userService.validateLoginRequest(loginRequest);
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenUtils.generateToken(userDetails);
        final UserDTO user = userService.getUserByEmail(loginRequest.getEmail());
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("token", token);
        resp.put("user", user);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/confirm-registration")
    public ResponseEntity<Object> confirmRegistration(@RequestParam("token") String token) {
        userService.confirmToken(token);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("msg", "Confirm registration successfully! Welcome to our system!");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/resend-confirm-code")
    public ResponseEntity<Object> resendConfirmRegistration(@RequestParam("email") String email) {
        userService.resendConfirmToken(email);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("msg", "Confirm registration successfully! Welcome to our system!");
        return ResponseEntity.ok(resp);
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
