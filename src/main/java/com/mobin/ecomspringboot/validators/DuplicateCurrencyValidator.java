package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateCurrency;
import com.mobin.ecomspringboot.generals.repositories.CurrencyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateCurrencyValidator  implements ConstraintValidator<DuplicateCurrency, String> {
    private final CurrencyRepository currencyRepository;
    public void initialize(DuplicateCurrency checkDuplicate) {
    }
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !currencyRepository.existsByCurrency(name);
    }
}
