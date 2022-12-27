package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    boolean existsByName(String name);
}
