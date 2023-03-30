package ecommerce.project.backend.controllers;

import ecommerce.project.backend.dto.CartDTO;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.requests.CartRequest;
import ecommerce.project.backend.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Cart")
public class CartController {
    private final CartService cartService;

    @PutMapping("/update-cart")
    @Operation(summary = "Add/Update product to/in user's cart")
    public ResponseEntity<Object> updateCart(@RequestBody @Valid CartRequest cartRequest) {
        CartDTO cart = cartService.updateCart(cartRequest);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("cartItem", cart);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
