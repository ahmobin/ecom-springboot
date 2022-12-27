package com.mobin.ecomspringboot.annotations;

import com.mobin.ecomspringboot.validators.DuplicateProductStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateProductStatusValidator.class)
public @interface DuplicateProductStatus{
    String message() default "Duplicate Product Status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
