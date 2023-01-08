package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.Unit;
import com.mobin.ecomspringboot.v1.generals.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UnitSeeder {
    private final UnitRepository unitRepo;

    public void createUnits(){
        Unit one = new Unit();
        Unit two = new Unit();
        Unit three = new Unit();
        List<Unit> units = new ArrayList<>();
        one.setName("KG");
        two.setName("Ltr");
        three.setName("Piece");
        units.add(one);
        units.add(two);
        units.add(three);
        unitRepo.saveAll(units);
    }
}
