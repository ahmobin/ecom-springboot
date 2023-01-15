package com.mobin.ecomspringboot.v1.generals.controllers;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import com.mobin.ecomspringboot.v1.generals.models.requests.AttributeUpdateRequest;
import com.mobin.ecomspringboot.v1.generals.services.AttributeValueService;
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
public class AttributeValueController {
    private final AttributeValueService attrValueService;

    @GetMapping(ApiEndpoints.PRODUCT_ATTR_VALUES_API)
    public ResponseEntity<List<AttributeValue>> index(@PathVariable UUID attrId){
        return ResponseEntity.of(Optional.of(attrValueService.attributeValues(attrId)));
    }

    @PostMapping(ApiEndpoints.PRODUCT_ATTR_VALUES_API)
    public ResponseEntity<AttributeValue> store(@PathVariable UUID attrId,
                                                @Valid @RequestParam String value){
        return ResponseEntity.status(HttpStatus.CREATED).body(attrValueService.store(attrId, value));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_ATTR_VALUES_API)
    public ResponseEntity<AttributeValue> show(@PathVariable UUID attrId,
                                               @Valid @PathVariable UUID valId){
        return ResponseEntity.status(HttpStatus.OK).body(attrValueService.show(attrId, valId));
    }

    @PutMapping(ApiEndpoints.PRODUCT_ATTR_VALUES_UPDATE_API)
    public ResponseEntity<AttributeValue> update(@PathVariable UUID attrId,
                                                 @Valid @RequestParam String value, @PathVariable UUID valId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(attrValueService.update(attrId, valId, value));
    }


    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_ATTR_VALUES_API)
    public ResponseEntity<AttributeValue> destroy(@PathVariable UUID attrId, @PathVariable UUID valId){
        attrValueService.delete(attrId, valId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
