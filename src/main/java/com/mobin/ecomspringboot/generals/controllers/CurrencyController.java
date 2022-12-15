package com.mobin.ecomspringboot.generals.controllers;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.generals.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

    @Autowired private CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<Currency> index(){
        return currencyService.currencyList();
    }


    @PostMapping("/currencies")
    public Currency store(@RequestBody CurrencyRequest currencyRequest){
        return currencyService.store(currencyRequest);
    }

    @GetMapping("/currencies/{id}")
    public Currency show(@PathVariable int id){
        return currencyService.show(id);
    }

    @PutMapping("/currencies/{id}/update")
    public Currency update(@RequestBody CurrencyRequest currencyRequest, @PathVariable int id){
        return currencyService.update(currencyRequest, id);
    }

    @DeleteMapping("/currencies/{id}")
    public void delete(@PathVariable int id){
        currencyService.destroy(id);
    }
}
