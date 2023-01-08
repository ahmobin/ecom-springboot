package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.inventory.entity.Brand;
import com.mobin.ecomspringboot.v1.inventory.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandSeeder {
    private final BrandRepository brandRepository;

    public void createBrand(){
        Brand one = new Brand();
        Brand two = new Brand();
        Brand three = new Brand();
        Brand four = new Brand();
        one.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        one.setName("Brand One");
        one.setSlug("brand-one");

        two.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        two.setName("Brand Two");
        two.setSlug("brand-two");

        three.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        three.setName("Brand Three");
        three.setSlug("brand-three");

        four.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        four.setName("Brand Four");
        four.setSlug("brand-four");

        List<Brand> brands = new ArrayList<>();
        brands.add(one);
        brands.add(two);
        brands.add(three);
        brands.add(four);

        brandRepository.saveAll(brands);
    }
}
