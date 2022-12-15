package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.generals.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

    @Autowired private CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> index() throws Exception {
        try{
            return ResponseEntity.of(Optional.of(currencyService.currencyList()));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }


    @PostMapping("/currencies")
    public Currency store(@Valid @RequestBody CurrencyRequest currencyRequest){
//        try{
            return currencyService.store(currencyRequest);
//            return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.store(currencyRequest));
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> show(@PathVariable int id) throws Exception {
        try{
            return ResponseEntity.of(Optional.of(currencyService.show(id)));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/currencies/{id}/update")
    public ResponseEntity<Currency> update(@RequestBody CurrencyRequest currencyRequest, @PathVariable int id) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(currencyService.update(currencyRequest, id));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> delete(@PathVariable int id) throws Exception {
        try{
            currencyService.destroy(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
