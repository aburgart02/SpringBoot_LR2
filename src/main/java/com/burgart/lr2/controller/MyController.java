package com.burgart.lr2.controller;

import com.burgart.lr2.exception.UnsupportedCodeException;
import com.burgart.lr2.exception.ValidationFailedException;
import com.burgart.lr2.model.*;
import com.burgart.lr2.service.AnnualBonusService;
import com.burgart.lr2.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyController {

    private final AnnualBonusService annualBonusService;

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Received request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .build();

        try {
            if (bindingResult.hasErrors()) {
                throw new ValidationFailedException(bindingResult.getFieldError().getDefaultMessage());
            }

            // Основная логика
            double annualBonus = annualBonusService.calculate(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays()
            );

            response.setCode(Codes.SUCCESS);
            response.setAnnualBonus(annualBonus);
            response.setErrorCode(ErrorCodes.EMPTY);
            response.setErrorMessage(ErrorMessages.EMPTY);

            log.info("Sending final successful response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.warn("Validation failed: {}", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.warn("Unsupported code: {}", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("An unexpected error occurred", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}