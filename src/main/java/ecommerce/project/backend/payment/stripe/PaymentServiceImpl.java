package ecommerce.project.backend.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public String createCustomer(StripeCustomerInformation customerInformation) throws Exception {
        if (isExistedUserEmail(customerInformation.getEmail())) {
            System.out.println("Email stripe customer is already taken!");
            throw new Exception("Email stripe customer is already taken!");
        }
        Map<String, Object> customerParams = Map.of("email", customerInformation.getEmail(), "name", customerInformation.getName());
        Customer newCustomer = Customer.create(customerParams);
        return newCustomer.getId();
    }

    private boolean isExistedUserEmail(String email) throws StripeException {
        boolean isExisted;
        Stripe.apiKey = stripeApiKey;
        Map<String, Object> checkParams = Map.of("email", email);
        CustomerCollection customers = Customer.list(checkParams);
        isExisted = !customers.getData().isEmpty();
        return isExisted;
    }
}
