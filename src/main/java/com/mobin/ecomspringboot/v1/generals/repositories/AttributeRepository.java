package com.mobin.ecomspringboot.v1.generals.repositories;

import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
    Optional<Attribute> findByName(String name);

    boolean existsByName(String name);
}
