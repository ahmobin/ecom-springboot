package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.Currency;
import com.mobin.ecomspringboot.v1.generals.repositories.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencySeeder {
    private final CurrencyRepository currencyRepo;


    public void createCurrencies(){
        Currency currency = new Currency();
        currency.setCurrency("BDT");
        currencyRepo.save(currency);
    }
}
