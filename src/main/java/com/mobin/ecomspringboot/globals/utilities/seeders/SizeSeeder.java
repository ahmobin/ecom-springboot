package com.mobin.ecomspringboot.globals.utilities.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SizeSeeder {
    private final SizeRepository sizeRepo;

    public void createSizes(){
        Size size1 = new Size();
        size1.setSize("Long");
        size1.setCode("L");
        sizeRepo.save(size1);

        Size size2 = new Size();
        size2.setSize("Medium");
        size2.setCode("M");
        sizeRepo.save(size2);
    }
}
