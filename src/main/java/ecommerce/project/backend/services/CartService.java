package ecommerce.project.backend.services;

import ecommerce.project.backend.dto.CartDTO;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.requests.CartRequest;

public interface CartService {
    Cart saveCart(Cart cart);
    CartDTO updateCart(CartRequest cartRequest);
}
