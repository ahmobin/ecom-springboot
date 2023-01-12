package com.mobin.ecomspringboot.v1.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateAttribute;
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
public class AttributeUpdateRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
}
