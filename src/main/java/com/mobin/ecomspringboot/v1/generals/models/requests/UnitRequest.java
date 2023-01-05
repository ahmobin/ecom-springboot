package com.mobin.ecomspringboot.v1.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequest {

    @NotEmpty(message = "Unit value cannot be empty")
    @NotNull(message = "Unit value cannot be null")
    @DuplicateUnit
    private String name;
}
