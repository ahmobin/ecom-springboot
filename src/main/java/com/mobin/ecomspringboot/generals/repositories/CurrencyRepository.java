package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    Currency findByCurrency(String name);

    boolean existsByCurrency(String name);
}
