package ecommerce.project.backend.controllers;

import ecommerce.project.backend.payment.stripe.PaymentService;
import ecommerce.project.backend.payment.stripe.StripeCustomerInformation;
import ecommerce.project.backend.requests.OrderRequest;
import ecommerce.project.backend.requests.StripeRequest;
import ecommerce.project.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }
}
