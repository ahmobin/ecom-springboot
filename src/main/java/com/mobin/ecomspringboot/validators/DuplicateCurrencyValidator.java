package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.CheckDuplicateCurrency;
import com.mobin.ecomspringboot.generals.repositories.CurrencyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateCurrencyValidator  implements ConstraintValidator<CheckDuplicateCurrency, String> {
    private final CurrencyRepository currencyRepository;
    public void initialize(CheckDuplicateCurrency checkDuplicate) {
    }
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !currencyRepository.existsByCurrency(name);
    }
}
