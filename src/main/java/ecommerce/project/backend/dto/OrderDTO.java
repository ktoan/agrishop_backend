package ecommerce.project.backend.dto;

import ecommerce.project.backend.entities.Address;
import ecommerce.project.backend.entities.OrderItem;
import ecommerce.project.backend.enums.OrderStatus;
import ecommerce.project.backend.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDTO {
    private Address address;
    private Set<OrderItem> orderItems = new HashSet<>();
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
}
