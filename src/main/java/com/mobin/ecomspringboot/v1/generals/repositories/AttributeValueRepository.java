package com.mobin.ecomspringboot.v1.generals.repositories;

import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> {

    List<AttributeValue> findAllByAttribute(Attribute attribute);

    Optional<AttributeValue> findByAttributeAndValue(Attribute attr, String value);
    Optional<AttributeValue> findByAttributeAndId(Attribute attr, UUID id);
    void deleteByAttributeAndId(Attribute attribute, UUID id);

    boolean existsByAttributeAndValue(Attribute attr, String name);
    boolean existsByAttributeAndId(Attribute attr, UUID id);
}
