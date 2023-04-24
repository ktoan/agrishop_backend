package ecommerce.project.backend.services.impl;

import com.stripe.param.TokenCreateParams;
import ecommerce.project.backend.entities.*;
import ecommerce.project.backend.payment.stripe.PaymentService;
import ecommerce.project.backend.payment.stripe.StripeCustomerInformation;
import ecommerce.project.backend.repositories.OrderItemRepository;
import ecommerce.project.backend.repositories.OrderRepository;
import ecommerce.project.backend.requests.OrderItemRequest;
import ecommerce.project.backend.requests.OrderRequest;
import ecommerce.project.backend.services.AddressService;
import ecommerce.project.backend.services.OrderService;
import ecommerce.project.backend.services.ProductService;
import ecommerce.project.backend.services.UserService;
import ecommerce.project.backend.utils.context.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ContextService contextService;
    private final UserService userService;
    private final PaymentService stripePaymentService;
    private final ProductService productService;
    private final AddressService addressService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public String createOrder(OrderRequest orderRequest) throws Exception {
        Order order = new Order();
        User user = contextService.loadUserFromContext();
        if (user.getStripeCustomerId() == null) {
            StripeCustomerInformation customerInformation = new StripeCustomerInformation(user.getFullName(), user.getEmail());
            String stripeCustomerId = stripePaymentService.createCustomer(customerInformation);
            user.setStripeCustomerId(stripeCustomerId);
            userService.saveUser(user);
        }
        order.setUser(user);
        Address address = addressService.findAddressById(orderRequest.getAddressId());
        order.setAddress(address);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order = saveOrder(order);
        long totalPrice = 0;
        for (OrderItemRequest orderItemRequest : orderRequest.getItems()) {
            Product product = productService.findProductById(orderItemRequest.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem = saveOrderItem(orderItem);
            order.addOrderItem(orderItem);
            totalPrice += product.getPrice().longValue() * orderItemRequest.getQuantity();
        }
        order = saveOrder(order);
        return null;
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    private OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
