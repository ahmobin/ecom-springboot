package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.ProductAttributeValue;
import com.mobin.ecomspringboot.generals.models.requests.ProductAttrValueRequest;
import com.mobin.ecomspringboot.generals.services.ProductAttrValueService;
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
public class ProductAttrValueController {

    private final ProductAttrValueService productAttrValueService;

    @GetMapping(ApiEndpoints.PRODUCT_ATTRIBUTE_VALUES_API)
    public ResponseEntity<List<ProductAttributeValue>> index(@PathVariable UUID productAttrId){
        return ResponseEntity.of(Optional.of(productAttrValueService.productAttrValues(productAttrId)));
    }

    @PostMapping(ApiEndpoints.PRODUCT_ATTRIBUTE_VALUES_API)
    public ResponseEntity<ProductAttributeValue> store(@PathVariable UUID productAttrId,
                                                       @Valid @RequestBody ProductAttrValueRequest productAttrValueRequest){
        System.out.println();
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttrValueService.store(productAttrId, productAttrValueRequest));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_ATTRIBUTE_VALUE_API)
    public ResponseEntity<ProductAttributeValue> show(@PathVariable UUID productAttrId,
                                                      @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productAttrValueService.show(productAttrId, id));
    }

    @PutMapping(ApiEndpoints.PRODUCT_ATTRIBUTE_VALUES_UPDATE_API)
    public ResponseEntity<ProductAttributeValue> update(@PathVariable UUID productAttrId,
                                                        @PathVariable UUID id,
                                                        @Valid @RequestBody ProductAttrValueRequest productAttrValueRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productAttrValueService.update(productAttrId, id, productAttrValueRequest));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_ATTRIBUTE_VALUE_API)
    public ResponseEntity<ProductAttributeValue> destroy(@PathVariable UUID productAttrId,
                                                         @PathVariable UUID id){
        productAttrValueService.delete(productAttrId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
