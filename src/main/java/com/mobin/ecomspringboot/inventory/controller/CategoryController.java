package com.mobin.ecomspringboot.inventory.controller;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.inventory.entity.Category;
import com.mobin.ecomspringboot.inventory.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(ApiEndpoints.CURRENCIES_API)
    public ResponseEntity<List<Category>> index(){
        return ResponseEntity.of(Optional.of(categoryService.categoryList()));
    }

    @PostMapping(ApiEndpoints.CATEGORIES_API)
    public ResponseEntity<Category> store(@RequestParam("file") MultipartFile file,
                                @Valid @NotNull @NotBlank @RequestParam String name) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.store(file, name));
    }
}
