package ecommerce.project.backend.services.impl;

import ecommerce.project.backend.dto.CartDTO;
import ecommerce.project.backend.entities.Cart;
import ecommerce.project.backend.entities.Product;
import ecommerce.project.backend.entities.User;
import ecommerce.project.backend.mappers.CartMapper;
import ecommerce.project.backend.repositories.CartRepository;
import ecommerce.project.backend.requests.CartRequest;
import ecommerce.project.backend.services.CartService;
import ecommerce.project.backend.services.ProductService;
import ecommerce.project.backend.services.UserService;
import ecommerce.project.backend.utils.context.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserService userService;
    private final ContextService contextService;
    private final ProductService productService;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public CartDTO updateCart(CartRequest cartRequest) {
        Cart cart = null;
        User user = contextService.loadUserFromContext();
        Product product = productService.findProductById(cartRequest.getProductId());
        if (user.getCart().stream().anyMatch(c -> c.getProduct().getId() == cartRequest.getProductId())) {
            cart = cartRepository.findByProductAndUser(product, user);
            long totalQuantity = cart.getQuantity() + cartRequest.getQuantity();
            cart.setQuantity(totalQuantity <= 0 ? 1 : totalQuantity);
        } else {
            cart = new Cart(product, cartRequest.getQuantity(), user);
        }
        cart = saveCart(cart);
        return cartMapper.toDTO(cart);
    }
}
