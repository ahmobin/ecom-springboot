package com.mobin.ecomspringboot.annotations;

import com.mobin.ecomspringboot.validators.DuplicateProductAttrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateProductAttrValidator.class)
public @interface DuplicateProductAttribute {
    String message() default "Duplicate Product Attribute";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
