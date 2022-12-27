package com.mobin.ecomspringboot.annotations;

import com.mobin.ecomspringboot.validators.DuplicateProductStatusValidator;
import com.mobin.ecomspringboot.validators.DuplicateStockStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateStockStatusValidator.class)
public @interface DuplicateStockStatus {
    String message() default "Duplicate Stock Status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
