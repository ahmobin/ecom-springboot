package com.mobin.ecomspringboot.generals.services;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.generals.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService {

    @Autowired private CurrencyRepository currencyRepo;


    public List<Currency> currencyList(){
        return currencyRepo.findAll();
    }


    public Currency store(CurrencyRequest currencyRequest){
        Currency currency = new Currency();
        currency.setCurrency(currencyRequest.getName().toUpperCase());
        return currencyRepo.save(currency);
    }


    public Currency show(UUID id){
        return currencyRepo.findById(id).get();
    }

    public Currency update(CurrencyRequest currencyRequest, UUID id){
        Currency update = new Currency();
        update.setCurrency(currencyRequest.getName().toUpperCase());
        update.setId(id);
        currencyRepo.save(update);
        return currencyRepo.findById(id).get();
    }

    public void destroy(UUID id){
        currencyRepo.deleteById(id);
    }



}
