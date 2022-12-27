package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateStockStatus;
import com.mobin.ecomspringboot.generals.repositories.StockStatusRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateStockStatusValidator implements ConstraintValidator<DuplicateStockStatus, String> {
    private final StockStatusRepository stockStatusRepository;
    public void initialize(DuplicateStockStatus duplicateProductStatus) {
    }
    public boolean isValid(String status, ConstraintValidatorContext context) {
        return !stockStatusRepository.existsByStatus(status);
    }
}
