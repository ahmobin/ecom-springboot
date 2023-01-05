package com.mobin.ecomspringboot.v1.generals.services;

import com.mobin.ecomspringboot.v1.generals.entities.ProductStatus;
import com.mobin.ecomspringboot.v1.generals.models.requests.ProductStatusRequest;
import com.mobin.ecomspringboot.v1.generals.repositories.ProductStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductStatusService {

    private final ProductStatusRepository productStatusRepo;

    public List<ProductStatus> productStatusList(){
        return productStatusRepo.findAll();
    }


    public ProductStatus store(ProductStatusRequest productStatusReq){
        ProductStatus productStatus = new ProductStatus();
        productStatus.setStatus(productStatusReq.getStatus());
        return productStatusRepo.save(productStatus);
    }

    public ProductStatus show(UUID id){
        productStatusValidation(id);
        return productStatusRepo.findById(id).get();
    }

    public ProductStatus update(ProductStatusRequest productStatusReq, UUID id){
        productStatusValidation(id);
        ProductStatus productStatus = new ProductStatus();
        productStatus.setId(id);
        productStatus.setStatus(productStatusReq.getStatus());
        return productStatusRepo.save(productStatus);
    }


    public void destroy(UUID id){
        productStatusValidation(id);
        productStatusRepo.deleteById(id);
    }


    private void productStatusValidation(UUID id){
        if(productStatusRepo.findById(id).isEmpty())  throw new EntityNotFoundException("Product Status not found on the id");
    }
}
