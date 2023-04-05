package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.CartDTO;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.requests.CartRequest;

public interface CartService {
    Cart saveCart(Cart cart);
    Cart findCartById(Long cartId);
    CartDTO updateCart(CartRequest cartRequest);
    void deleteCartItem(Long cartItemId);
}
