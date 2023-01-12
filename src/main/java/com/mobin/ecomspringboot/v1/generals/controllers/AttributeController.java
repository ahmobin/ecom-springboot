package com.mobin.ecomspringboot.v1.generals.controllers;

import com.mobin.ecomspringboot.annotations.DuplicateAttribute;
import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.models.requests.AttributeUpdateRequest;
import com.mobin.ecomspringboot.v1.generals.models.requests.ProductAttributeRequest;
import com.mobin.ecomspringboot.v1.generals.services.AttributeService;
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
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping(ApiEndpoints.PRODUCT_ATTR_API)
    public ResponseEntity<List<Attribute>> index(){
        return ResponseEntity.of(Optional.of(attributeService.attributes()));
    }

    @PostMapping(ApiEndpoints.PRODUCT_ATTR_API)
    public ResponseEntity<Attribute> store(@Valid @RequestBody ProductAttributeRequest attributeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(attributeService.store(attributeRequest));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_ATTR_API)
    public ResponseEntity<Attribute> show(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(attributeService.show(id));
    }

    @PutMapping(ApiEndpoints.PRODUCT_ATTR_UPDATE_API)
    public ResponseEntity<Attribute> update(@PathVariable UUID id,
                                            @Valid @RequestBody AttributeUpdateRequest attributeRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(attributeService.update(id, attributeRequest));
    }


    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_ATTR_API)
    public ResponseEntity<Attribute> destroy(@PathVariable UUID id){
        attributeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
