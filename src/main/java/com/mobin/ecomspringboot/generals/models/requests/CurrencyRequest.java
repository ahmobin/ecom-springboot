package com.mobin.ecomspringboot.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateCurrency;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class CurrencyRequest {
    @NotNull(message = "Currency name can not be null.")
    @NotEmpty(message = "Currency name can not be empty.")
    @DuplicateCurrency
    private String name;
}
