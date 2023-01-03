package com.mobin.ecomspringboot.inventory.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.inventory.entity.Brand;
import com.mobin.ecomspringboot.inventory.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class BrandController {
    private final BrandService brandService;

    @GetMapping(ApiEndpoints.BRANDS_API)
    public ResponseEntity<List<Brand>> index(){
        return ResponseEntity.of(Optional.of(brandService.brandList()));
    }

    @PostMapping(ApiEndpoints.BRANDS_API)
    public ResponseEntity<Brand> store(@RequestParam MultipartFile file,
                                       @RequestParam String name) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(file, name));
    }

    @GetMapping(ApiEndpoints.SINGLE_BRANDS_API)
    public ResponseEntity<Brand> show(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.show(id));
    }

    @PutMapping(ApiEndpoints.BRANDS_UPDATE_API)
    public ResponseEntity<Brand> update(@RequestParam MultipartFile file,
                                        @RequestParam String name,
                                        @PathVariable UUID id) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(brandService.update(file, name, id));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_BRANDS_API)
    public ResponseEntity<Brand> destroy(@PathVariable UUID id) throws IOException {
        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
