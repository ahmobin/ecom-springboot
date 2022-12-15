package com.mobin.ecomspringboot.generals.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyRequest {
    @NotNull
    private String name;
}
