package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateProductStatus;
import com.mobin.ecomspringboot.generals.repositories.ProductStatusRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateProductStatusValidator implements ConstraintValidator<DuplicateProductStatus, String> {
    private final ProductStatusRepository productStatusRepository;
    public void initialize(DuplicateProductStatus duplicateProductStatus) {
    }
    public boolean isValid(String status, ConstraintValidatorContext context) {
        return !productStatusRepository.existsByStatus(status);
    }
}
