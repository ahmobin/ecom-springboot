package com.mobin.ecomspringboot.v1.generals.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.v1.generals.entities.Currency;
import com.mobin.ecomspringboot.v1.generals.models.requests.CurrencyRequest;
import com.mobin.ecomspringboot.v1.generals.repositories.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepo;

    public List<Currency> currencyList(){
        return currencyRepo.findAll();
    }


    public Currency store(CurrencyRequest currencyRequest){
//        currencyValidation(currencyRequest.getName().toUpperCase());

        Currency currency = new Currency();
        currency.setCurrency(currencyRequest.getName().toUpperCase());
        return currencyRepo.save(currency);
    }


    public Currency show(UUID id){
        return currencyRepo.findById(id).get();
    }

    public Currency update(CurrencyRequest currencyRequest, UUID id){
//        currencyValidation(currencyRequest.getName().toUpperCase());

        Currency update = new Currency();
        update.setCurrency(currencyRequest.getName().toUpperCase());
        update.setId(id);
        currencyRepo.save(update);
        return currencyRepo.findById(id).get();
    }

    public void destroy(UUID id){
        currencyRepo.deleteById(id);
    }


//    private void currencyValidation(String name){
//        if(currencyRepo.findCurrencyByCurrency(name) != null)  throw new DuplicateDataException("Currency name already exist");
//    }

}
