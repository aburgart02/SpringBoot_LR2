package com.burgart.lr2.service;

import com.burgart.lr2.exception.UnsupportedCodeException;
import com.burgart.lr2.exception.ValidationFailedException;
import com.burgart.lr2.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    @Override
    public void isValidUid(Request request) throws UnsupportedCodeException {
        if (request.getUid().equals("123")) {
            throw new UnsupportedCodeException("Uid 123 не поддерживается");
        }
    }
}