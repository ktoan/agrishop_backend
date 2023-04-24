package ecommerce.project.backend.payment.stripe;

import com.stripe.exception.StripeException;

public interface PaymentService {
    String createCustomer(StripeCustomerInformation customerInformation) throws Exception;
}
