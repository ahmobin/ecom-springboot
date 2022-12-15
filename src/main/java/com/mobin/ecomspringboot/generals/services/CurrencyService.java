package com.mobin.ecomspringboot.generals.services;

import com.mobin.ecomspringboot.generals.entities.Currency;
import com.mobin.ecomspringboot.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.generals.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired private CurrencyRepository currencyRepo;


    public List<Currency> currencyList(){
        return currencyRepo.findAll();
    }


    public Currency store(CurrencyRequest currencyReq){
        Currency currency = new Currency();
        currency.setCurrency(currencyReq.getName());
        return currencyRepo.save(currency);
    }


    public Currency show(int id){
        return currencyRepo.findById(id).get();
    }

    public Currency update(CurrencyRequest currencyRequest, int id){
        Currency update = new Currency();
        update.setCurrency(currencyRequest.getName());
        update.setId(id);
        return currencyRepo.save(update);
    }

    public void destroy(int id){
        currencyRepo.deleteById(id);
    }



}
