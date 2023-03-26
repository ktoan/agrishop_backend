package ecommerce.project.backend.services;

import ecommerce.project.backend.entities.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken findByTokenValue(String token);
    void saveToken(ConfirmationToken confirmationToken);
}
