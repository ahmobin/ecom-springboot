package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.StockStatus;
import com.mobin.ecomspringboot.generals.models.requests.StockStatusRequest;
import com.mobin.ecomspringboot.generals.services.StockStatusService;
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
public class StockStatusController {
    private final StockStatusService stockStatusService;

    @GetMapping(ApiEndpoints.STOCK_STATUSES_API)
    public ResponseEntity<List<StockStatus>> index(){
        return ResponseEntity.of(Optional.of(stockStatusService.productStatusList()));
    }

    @PostMapping(ApiEndpoints.STOCK_STATUSES_API)
    public ResponseEntity<StockStatus> store(@Valid @RequestBody StockStatusRequest stockStatusRequest){
        return ResponseEntity.of(Optional.of(stockStatusService.store(stockStatusRequest)));
    }

    @GetMapping(ApiEndpoints.SINGLE_STOCK_STATUSES_API)
    public ResponseEntity<StockStatus> show(@PathVariable UUID id){
        return ResponseEntity.of(Optional.of(stockStatusService.show(id)));
    }

    @PutMapping(ApiEndpoints.STOCK_STATUSES_UPDATE_API)
    public ResponseEntity<StockStatus> update(@Valid @RequestBody StockStatusRequest stockStatusRequest, @PathVariable UUID id){
        return ResponseEntity.of(Optional.of(stockStatusService.update(stockStatusRequest, id)));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_STOCK_STATUSES_API)
    public ResponseEntity<StockStatus> delete(@PathVariable UUID id){
        stockStatusService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
