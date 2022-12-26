package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.generals.services.CurrencyService;
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
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping(ApiEndpoints.CURRENCIES_API)
    public ResponseEntity<List<Currency>> index(){
        return ResponseEntity.of(Optional.of(currencyService.currencyList()));
    }


    @PostMapping(ApiEndpoints.CURRENCIES_API)
    public ResponseEntity<Currency> store(@Valid @RequestBody CurrencyRequest currencyRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.store(currencyRequest));
    }

    @GetMapping(ApiEndpoints.SINGLE_CURRENCIES_API)
    public ResponseEntity<Currency> show(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.show(id));
    }

    @PutMapping(ApiEndpoints.CURRENCIES_UPDATE_API)
    public ResponseEntity<Currency> update(@Valid @RequestBody CurrencyRequest currencyRequest, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(currencyService.update(currencyRequest, id));
    }

    @DeleteMapping(ApiEndpoints.SINGLE_CURRENCIES_API)
    public ResponseEntity<Currency> delete(@PathVariable UUID id){
        currencyService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
