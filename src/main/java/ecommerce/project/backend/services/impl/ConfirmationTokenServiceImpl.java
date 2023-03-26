package ecommerce.project.backend.services.impl;

import ecommerce.project.backend.entities.ConfirmationToken;
import ecommerce.project.backend.exceptions.BadRequestException;
import ecommerce.project.backend.exceptions.NotFoundException;
import ecommerce.project.backend.exceptions.ServerErrorException;
import ecommerce.project.backend.repositories.ConfirmationTokenRepository;
import ecommerce.project.backend.services.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.Optional;

import static ecommerce.project.backend.constants.Messaging.TOKEN_NOT_FOUND_VALUE_MSG;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken findByTokenValue(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException(String.format(TOKEN_NOT_FOUND_VALUE_MSG, token)));
    }

    @Override
    public void saveToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
}
