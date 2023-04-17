package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.CartDTO;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.requests.CartRequest;

import java.util.List;

public interface CartService {
    List<CartDTO> fetchCartOfSessionUser();
    Cart saveCart(Cart cart);
    Cart findCartById(Long cartId);
    CartDTO updateCart(CartRequest cartRequest);
    void deleteCartItem(Long cartItemId);
}
