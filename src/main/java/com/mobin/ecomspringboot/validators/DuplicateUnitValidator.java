package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateUnit;
import com.mobin.ecomspringboot.generals.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateUnitValidator implements ConstraintValidator<DuplicateUnit, String> {
    private final UnitRepository unitRepository;
    public void initialize(DuplicateUnit duplicateUnit) {
    }
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !unitRepository.existsByName(name);
    }
}
