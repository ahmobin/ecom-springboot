package com.mobin.ecomspringboot.v1.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateProductAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductAttributeRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    @DuplicateProductAttribute
    private String name;
}
