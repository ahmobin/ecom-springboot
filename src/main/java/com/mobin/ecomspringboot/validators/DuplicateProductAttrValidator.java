package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateProductAttribute;
import com.mobin.ecomspringboot.generals.repositories.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateProductAttrValidator implements ConstraintValidator<DuplicateProductAttribute, String> {
    private final ProductAttributeRepository productAttrRepo;
    public void initialize(DuplicateProductAttribute checkDuplicate) {
    }
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !productAttrRepo.existsByName(name);
    }
}
