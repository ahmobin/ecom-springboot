package com.mobin.ecomspringboot.v1.generals.models.requests;

import com.mobin.ecomspringboot.annotations.DuplicateProductStatus;
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
public class ProductStatusRequest {

    @NotEmpty(message = "Status value cannot be empty")
    @NotNull(message = "Status value cannot be null")
    @DuplicateProductStatus
    private String status;
}
