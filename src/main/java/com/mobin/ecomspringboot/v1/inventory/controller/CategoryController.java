package com.mobin.ecomspringboot.v1.inventory.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.v1.inventory.entity.Category;
import com.mobin.ecomspringboot.v1.inventory.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(ApiEndpoints.CATEGORIES_API)
    public ResponseEntity<List<Category>> index(){
        return ResponseEntity.of(Optional.of(categoryService.categoryList()));
    }

    @PostMapping(ApiEndpoints.CATEGORIES_API)
    public ResponseEntity<Category> store(@RequestParam("file") MultipartFile file,
                                @Valid @NotNull @NotBlank @RequestParam String name) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.store(file, name));
    }

    @GetMapping(ApiEndpoints.SINGLE_CATEGORIES_API)
    public ResponseEntity<Category> show(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.show(id));
    }

    @PutMapping(ApiEndpoints.PRODUCT_CATEGORIES_UPDATE_API)
    public ResponseEntity<Category> update(@RequestParam("file") MultipartFile file,
                                           @Valid @NotNull @NotBlank @RequestParam String name, @PathVariable UUID id) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.update(file, name, id));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_CATEGORIES_API)
    public ResponseEntity<Category> delete(@PathVariable UUID id){
        categoryService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
