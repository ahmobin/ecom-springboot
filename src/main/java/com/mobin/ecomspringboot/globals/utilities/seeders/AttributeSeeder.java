package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttributeSeeder {
    private final AttributeRepository attrRepo;
    private final AttributeValueRepository attrValueRepo;

    public void createAttrs(){
        Attribute attr1 = new Attribute();
        attr1.setName("Color");
        AttributeValue attr1Value1 = new AttributeValue();
    }
}
