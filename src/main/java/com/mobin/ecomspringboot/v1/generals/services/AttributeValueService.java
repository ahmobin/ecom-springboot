package com.mobin.ecomspringboot.v1.generals.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.globals.helpers.StringManageable;
import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeValueService {
    private final AttributeRepository attrRepo;
    private final AttributeValueRepository attrValueRepo;

    public List<AttributeValue> attributeValues(UUID attrId){
        return attrValueRepo.findAllByAttribute(attrRepo.findById(attrId).orElseThrow(()->{
            throw new EntityNotFoundException("Attribute Not Found");
        }));
    }


    public AttributeValue store(UUID attrId, String value){
        isExist(attrId, value);
        AttributeValue attrValue = new AttributeValue();
        attrValue.setValue(StringManageable.capitalize(value));
        attrValue.setAttribute(isAttributeExist(attrId));
        return attrValueRepo.save(attrValue);
    }

    public AttributeValue show(UUID attrId, UUID id){
        return attrValueRepo.findByAttributeAndId(isAttributeExist(attrId),id).orElseThrow(()->new EntityNotFoundException("Attribute not found on the id"));
    }


    public AttributeValue update(UUID attrId, UUID id, String value){
        validation(attrId, value, id);

        AttributeValue attrValue = attrValueRepo.findByAttributeAndId(isAttributeExist(attrId), id).get();
        attrValue.setValue(value);
        attrValueRepo.save(attrValue);
        return show(attrId, id);
    }


    @Transactional
    public void delete(UUID attrId, UUID id){
        attrValueRepo.deleteByAttributeAndId(isAttributeExist(attrId), id);
    }

    private void validation(UUID attrId, String value, UUID id){
        if(!attrValueRepo.existsByAttributeAndId(attrRepo.findById(attrId).get(), id)){
            throw new EntityNotFoundException("Attribute value not found");
        }
        
        if (attrValueRepo.existsByAttributeAndValue(isAttributeExist(attrId), value) && !attrValueRepo.findByAttributeAndValue(isAttributeExist(attrId), value).get().getId().equals(id))
            throw new DuplicateDataException("Attribute value already exist");
    }

    private void isExist(UUID attrId , String value){
        isAttributeExist(attrId);
        if(attrValueRepo.existsByAttributeAndValue(attrRepo.findById(attrId).get(), value))
            throw new DuplicateDataException("Attribute value already exist");
    }
    
    private Attribute isAttributeExist(UUID id){
        return attrRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Attribute not found");});
    }
}
