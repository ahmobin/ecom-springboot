package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.annotations.DuplicateAttribute;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicateAttributeValidator   implements ConstraintValidator<DuplicateAttribute, String> {

    private final AttributeRepository attrRepo;
    public void initialize(DuplicateAttribute checkDuplicate) {
    }
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !attrRepo.existsByName(name);
    }


}
