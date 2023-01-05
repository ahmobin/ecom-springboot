package com.mobin.ecomspringboot.v1.generals.services;


import com.mobin.ecomspringboot.v1.generals.entities.Unit;
import com.mobin.ecomspringboot.v1.generals.models.requests.UnitRequest;
import com.mobin.ecomspringboot.v1.generals.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public List<Unit> unitList(){
        return unitRepository.findAll();
    }


    public Unit store(UnitRequest unitReq){
        Unit unit = new Unit();
        unit.setName(unitReq.getName());
        return unitRepository.save(unit);
    }

    public Unit show(UUID id){
        unitValidation(id);
        return unitRepository.findById(id).get();
    }

    public Unit update(UnitRequest unitReq, UUID id){
        unitValidation(id);
        Unit productStatus = new Unit();
        productStatus.setId(id);
        productStatus.setName(unitReq.getName());
        return unitRepository.save(productStatus);
    }


    public void destroy(UUID id){
        unitValidation(id);
        unitRepository.deleteById(id);
    }


    private void unitValidation(UUID id){
        if(unitRepository.findById(id).isEmpty())  throw new EntityNotFoundException("Unit not found on the id");
    }
}
