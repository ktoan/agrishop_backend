package ecommerce.project.backend.payment.stripe;

import com.stripe.exception.StripeException;
import ecommerce.project.backend.requests.StripeRequest;

public interface PaymentService {
    String createCardToken(StripeRequest stripeRequest, String stripeCustomerId) throws StripeException;
    String createCustomer(StripeCustomerInformation customerInformation) throws Exception;
}
