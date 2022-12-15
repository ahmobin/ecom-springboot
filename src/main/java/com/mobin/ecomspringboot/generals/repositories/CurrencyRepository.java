package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
