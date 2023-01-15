package com.mobin.ecomspringboot.v1.generals.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.globals.helpers.StringManageable;
import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.models.requests.AttributeUpdateRequest;
import com.mobin.ecomspringboot.v1.generals.models.requests.ProductAttributeRequest;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeRepository attrRepo;

    public List<Attribute> attributes(){
        return attrRepo.findAll();
    }


    public Attribute store(ProductAttributeRequest attributeRequest){
        isExist(attributeRequest.getName());
        Attribute attribute = new Attribute();
        attribute.setName(StringManageable.capitalize(attributeRequest.getName()));
        return attrRepo.save(attribute);
    }

    public Attribute show(UUID id){
        return attrRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Attribute not found on the id"));
    }


    public Attribute update(UUID id, AttributeUpdateRequest attributeRequest){
        validation(attributeRequest.getName(), id);

        Attribute attribute = attrRepo.findById(id).get();
        attribute.setName(StringManageable.capitalize(attributeRequest.getName()));
        attrRepo.save(attribute);
        return show(id);
    }


    public void delete(UUID id){
        attrRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Attribute not found on the id"));
        attrRepo.deleteById(id);
    }

    private void validation(String name, UUID id){
        if(!attrRepo.existsById(id)){
            throw new EntityNotFoundException("Attribute not found");
        }

        if (attrRepo.existsByName(name) && !attrRepo.findByName(name).get().getId().equals(id))
            throw new DuplicateDataException("Attribute name already exist");
    }

    private void isExist(String name){
        if(attrRepo.existsByName(name))
            throw new DuplicateDataException("Attribute name already exist");
    }

}
