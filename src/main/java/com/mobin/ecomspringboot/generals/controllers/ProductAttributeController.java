package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.annotations.DuplicateProductAttribute;
import com.mobin.ecomspringboot.generals.entities.ProductAttribute;
import com.mobin.ecomspringboot.generals.models.requests.ProductAttributeRequest;
import com.mobin.ecomspringboot.generals.services.ProductAttributeService;
import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductAttributeController {

    private final ProductAttributeService productAttrService;

    @GetMapping(ApiEndpoints.PRODUCT_ATTRIBUTES_API)
    public ResponseEntity<List<ProductAttribute>> index(){
        return ResponseEntity.of(Optional.of(productAttrService.productAttributes()));
    }

    @PostMapping(ApiEndpoints.PRODUCT_ATTRIBUTES_API)
    public ResponseEntity<ProductAttribute> store(@Valid @RequestBody ProductAttributeRequest productAttributeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttrService.store(productAttributeRequest));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_ATTRIBUTES_API)
    public ResponseEntity<ProductAttribute> store(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productAttrService.show(id));
    }

    @PutMapping(ApiEndpoints.PRODUCT_ATTRIBUTES_UPDATE_API)
    public ResponseEntity<ProductAttribute> update(@PathVariable UUID id, @RequestBody ProductAttributeRequest productAttributeRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productAttrService.update(id, productAttributeRequest));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_ATTRIBUTES_API)
    public ResponseEntity<ProductAttribute> destroy(@PathVariable UUID id){
        productAttrService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
