package com.burgart.lr2.service;

import com.burgart.lr2.exception.UnsupportedCodeException;
import com.burgart.lr2.exception.ValidationFailedException;
import com.burgart.lr2.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().toString();
            log.error("Validation failed: {}", errorMessage);
            throw new ValidationFailedException(errorMessage);
        }
    }

    @Override
    public void isValidUid(Request request) throws UnsupportedCodeException {
        if (request.getUid().equals("123")) {
            String errorMessage = "Uid 123 не поддерживается";
            log.error(errorMessage);
            throw new UnsupportedCodeException(errorMessage);
        }
    }
}