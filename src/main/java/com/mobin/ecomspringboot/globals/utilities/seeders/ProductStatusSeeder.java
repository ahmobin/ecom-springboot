package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.ProductStatus;
import com.mobin.ecomspringboot.v1.generals.repositories.ProductStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductStatusSeeder {
    private final ProductStatusRepository productStatusRepo;

    public void createProductStatus(){
        ProductStatus one = new ProductStatus();
        ProductStatus two = new ProductStatus();
        one.setStatus("Active");
        two.setStatus("Inactive");
        List<ProductStatus> statusList = new ArrayList<>();
        statusList.add(one);
        statusList.add(two);
        productStatusRepo.saveAll(statusList);
    }
}
