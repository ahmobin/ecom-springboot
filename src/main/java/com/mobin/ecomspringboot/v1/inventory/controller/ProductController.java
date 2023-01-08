package com.mobin.ecomspringboot.v1.inventory.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(ApiEndpoints.PRODUCTS_API)
    public ResponseEntity<List<Product>> index(){
        return ResponseEntity.of(Optional.of(productService.products()));
    }
}
