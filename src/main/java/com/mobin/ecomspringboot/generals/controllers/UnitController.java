package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.Unit;
import com.mobin.ecomspringboot.generals.models.requests.UnitRequest;
import com.mobin.ecomspringboot.generals.services.UnitService;
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
public class UnitController {
    private final UnitService unitService;

    @GetMapping(ApiEndpoints.PRODUCT_UNITS_API)
    public ResponseEntity<List<Unit>> index(){
        return ResponseEntity.of(Optional.of(unitService.unitList()));
    }

    @PostMapping(ApiEndpoints.PRODUCT_UNITS_API)
    public ResponseEntity<Unit> store(@Valid @RequestBody UnitRequest unitRequest){
        return ResponseEntity.of(Optional.of(unitService.store(unitRequest)));
    }

    @GetMapping(ApiEndpoints.SINGLE_PRODUCT_UNITS_API)
    public ResponseEntity<Unit> show(@PathVariable UUID id){
        return ResponseEntity.of(Optional.of(unitService.show(id)));
    }

    @PutMapping(ApiEndpoints.PRODUCT_UNITS_UPDATE_API)
    public ResponseEntity<Unit> update(@Valid @RequestBody UnitRequest unitRequest, @PathVariable UUID id){
        return ResponseEntity.of(Optional.of(unitService.update(unitRequest, id)));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_PRODUCT_UNITS_API)
    public ResponseEntity<Unit> delete(@PathVariable UUID id){
        unitService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
