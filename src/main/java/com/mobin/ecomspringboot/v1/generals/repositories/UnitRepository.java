package com.mobin.ecomspringboot.v1.generals.repositories;

import com.mobin.ecomspringboot.v1.generals.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {
    boolean existsByName(String name);
}
