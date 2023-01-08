package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.StockStatus;
import com.mobin.ecomspringboot.v1.generals.repositories.StockStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockStatusSeeder {
    private final StockStatusRepository stockStatusRepo;

    public void createStockStatus(){
        StockStatus one = new StockStatus();
        StockStatus two = new StockStatus();
        StockStatus three = new StockStatus();
        List<StockStatus> statusList = new ArrayList<>();
        one.setStatus("In Stock");
        two.setStatus("Stock Out");
        three.setStatus("Hold");
        statusList.add(one);
        statusList.add(two);
        statusList.add(three);
        stockStatusRepo.saveAll(statusList);
    }
}
