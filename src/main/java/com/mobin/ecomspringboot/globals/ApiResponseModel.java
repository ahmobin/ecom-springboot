package com.mobin.ecomspringboot.globals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseModel {
    private int code;
    private String message;

    private List<Object> data;

    public ApiResponseModel(int i, String statusData, List<Object> objects, HttpStatus badRequest) {
    }
}
