package com.mobin.ecomspringboot.generals.services;

import com.mobin.ecomspringboot.generals.entities.StockStatus;
import com.mobin.ecomspringboot.generals.models.requests.StockStatusRequest;
import com.mobin.ecomspringboot.generals.repositories.StockStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockStatusService {

    private final StockStatusRepository stockStatusRepo;

    public List<StockStatus> productStatusList(){
        return stockStatusRepo.findAll();
    }


    public StockStatus store(StockStatusRequest productStatusReq){
        StockStatus productStatus = new StockStatus();
        productStatus.setStatus(productStatusReq.getStatus());
        return stockStatusRepo.save(productStatus);
    }

    public StockStatus show(UUID id){
        productStatusValidation(id);
        return stockStatusRepo.findById(id).get();
    }

    public StockStatus update(StockStatusRequest productStatusReq, UUID id){
        productStatusValidation(id);
        StockStatus productStatus = new StockStatus();
        productStatus.setId(id);
        productStatus.setStatus(productStatusReq.getStatus());
        return stockStatusRepo.save(productStatus);
    }


    public void destroy(UUID id){
        productStatusValidation(id);
        stockStatusRepo.deleteById(id);
    }


    private void productStatusValidation(UUID id){
        if(stockStatusRepo.findById(id).isEmpty())  throw new EntityNotFoundException("Stock Status not found on the id");
    }
}
