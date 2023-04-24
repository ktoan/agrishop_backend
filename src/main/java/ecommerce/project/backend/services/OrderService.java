package ecommerce.project.backend.services;

import ecommerce.project.backend.requests.OrderRequest;

public interface OrderService {
    String createOrder(OrderRequest orderRequest) throws Exception;
}
