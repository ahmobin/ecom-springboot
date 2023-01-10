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

    public void createAttrsWithValue(){
        Attribute attr1 = new Attribute();
        attr1.setName("Color");
        attrRepo.save(attr1);

        Attribute attr2 = new Attribute();
        attr2.setName("Size");
        attrRepo.save(attr2);

        AttributeValue attr1Value1 = new AttributeValue();
        attr1Value1.setAttribute(attr1);
        attr1Value1.setValue("Black");
        attrValueRepo.save(attr1Value1);

        AttributeValue attr2Value1 = new AttributeValue();
        attr2Value1.setAttribute(attr2);
        attr2Value1.setValue("XL");
        attrValueRepo.save(attr2Value1);

        AttributeValue attr2Value2 = new AttributeValue();
        attr2Value2.setAttribute(attr2);
        attr2Value2.setValue("L");
        attrValueRepo.save(attr2Value2);

        AttributeValue attr2Value3 = new AttributeValue();
        attr2Value3.setAttribute(attr2);
        attr2Value3.setValue("M");
        attrValueRepo.save(attr2Value3);
    }
}
