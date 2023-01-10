package com.mobin.ecomspringboot.globals.utilities;

import com.mobin.ecomspringboot.globals.utilities.seeders.*;
import com.mobin.ecomspringboot.v1.inventory.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupUtility implements CommandLineRunner {

    private final CategorySeeder categorySeeder;
    private final BrandSeeder brandSeeder;
    private final UnitSeeder unitSeeder;
    private final AttributeSeeder attributeSeeder;
    private final ProductSeeder productSeeder;
    @Override
    public void run(String... args) throws Exception {

        brandSeeder.createBrand();

        Category category = categorySeeder.createCategory(1);
        for (int i = 1; i<3; i++){
            categorySeeder.createSubCategory(i, category);
        }

        unitSeeder.createUnits();

        attributeSeeder.createAttrsWithValue();

        productSeeder.simpleProductCreate();

        productSeeder.advanceProductCreate();
    }
}
