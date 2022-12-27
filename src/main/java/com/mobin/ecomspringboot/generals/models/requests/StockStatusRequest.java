package com.mobin.ecomspringboot.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateProductStatus;
import com.mobin.ecomspringboot.annotations.DuplicateStockStatus;
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
public class StockStatusRequest {

    @NotEmpty(message = "Status value cannot be empty")
    @NotNull(message = "Status value cannot be null")
    @DuplicateStockStatus
    private String status;
}
