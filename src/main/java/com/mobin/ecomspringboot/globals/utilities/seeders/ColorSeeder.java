package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.Color;
import com.mobin.ecomspringboot.v1.generals.repositories.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorSeeder {
    private final ColorRepository colorRepo;

    public void createColors(){
        Color color1 = new Color();
        color1.setColor("Black");
        color1.setCode("#000000");
        colorRepo.save(color1);

        Color color2 = new Color();
        color2.setColor("White");
        color2.setCode("#FFFFFF");
        colorRepo.save(color2);
    }
}
