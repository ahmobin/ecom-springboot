package com.mobin.ecomspringboot.v1.generals.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductVariation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "product_attribute_values")
public class ProductAttributeValue {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private ProductAttribute productAttribute;

    @OneToMany
    @JsonIgnore
    private List<ProductVariation> productVariations;

    @Column(nullable = false, length = 128)
    private String value;

    @Column(length = 32)
    private String code;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
