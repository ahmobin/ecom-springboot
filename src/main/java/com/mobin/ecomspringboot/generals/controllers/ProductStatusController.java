package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.ProductStatus;
import com.mobin.ecomspringboot.generals.models.requests.ProductStatusRequest;
import com.mobin.ecomspringboot.generals.services.ProductStatusService;
import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductStatusController {
    private final ProductStatusService productStatusService;

    @GetMapping(ApiEndpoints.PRODUCT_STATUSES_API)
    public ResponseEntity<List<ProductStatus>> index(){
        return ResponseEntity.of(Optional.of(productStatusService.productStatusList()));
    }

    @PostMapping(ApiEndpoints.PRODUCT_STATUSES_API)
    public ResponseEntity<ProductStatus> store(@Valid @RequestBody ProductStatusRequest productStatusRequest){
        return ResponseEntity.of(Optional.of(productStatusService.store(productStatusRequest)));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_STATUSES_API)
    public ResponseEntity<ProductStatus> show(@PathVariable UUID id){
        return ResponseEntity.of(Optional.of(productStatusService.show(id)));
    }

    @PutMapping(ApiEndpoints.PRODUCT_STATUSES_UPDATE_API)
    public ResponseEntity<ProductStatus> update(@Valid @RequestBody ProductStatusRequest productStatusRequest, @PathVariable UUID id){
        return ResponseEntity.of(Optional.of(productStatusService.update(productStatusRequest, id)));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_STATUSES_API)
    public ResponseEntity<ProductStatus> delete(@PathVariable UUID id){
        productStatusService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
