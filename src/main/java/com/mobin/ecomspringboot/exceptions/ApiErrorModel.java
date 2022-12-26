package com.mobin.ecomspringboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiErrorModel {
    private int code;
    private String message;

    private List<String> errors;
}
