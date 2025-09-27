package com.burgart.lr2.service;

import com.burgart.lr2.exception.UnsupportedCodeException;
import com.burgart.lr2.exception.ValidationFailedException;
import com.burgart.lr2.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void isValidUid(Request request) throws UnsupportedCodeException;
}