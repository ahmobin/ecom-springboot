package com.mobin.ecomspringboot.inventory.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.inventory.entity.SubCategory;
import com.mobin.ecomspringboot.inventory.services.SubCategoryService;
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

@RequiredArgsConstructor
@RestController
@Validated
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping(ApiEndpoints.SUB_CATEGORIES_API)
    public ResponseEntity<List<SubCategory>> index(@PathVariable UUID categoryId){
        return ResponseEntity.of(Optional.of(subCategoryService.subCategoryList(categoryId)));
    }

    @PostMapping(ApiEndpoints.SUB_CATEGORIES_API)
    public ResponseEntity<SubCategory> store(@PathVariable UUID categoryId,
                                             @RequestParam("file") MultipartFile file,
                                             @Valid @NotNull @NotBlank @RequestParam String name) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.store(categoryId, file, name));
    }

    @GetMapping(ApiEndpoints.SINGLE_SUB_CATEGORIES_API)
    public ResponseEntity<SubCategory> show(@PathVariable UUID categoryId,
                                            @PathVariable UUID id) {
        return ResponseEntity.ok(subCategoryService.show(categoryId, id));
    }

    @PutMapping(ApiEndpoints.PRODUCT_SUB_CATEGORIES_UPDATE_API)
    public ResponseEntity<SubCategory> update(@PathVariable UUID categoryId,
                                              @RequestParam("file") MultipartFile file,
                                              @Valid @NotNull @NotBlank @RequestParam String name,
                                              @PathVariable UUID id) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(subCategoryService.update(categoryId, file, name, id));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_SUB_CATEGORIES_API)
    public ResponseEntity<SubCategory> delete(@PathVariable UUID categoryId,
                                              @PathVariable UUID id){
        subCategoryService.destroy(categoryId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
