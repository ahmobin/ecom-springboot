package com.mobin.ecomspringboot.v1.generals.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "product_attribute_values")
public class AttributeValue {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "char-uuid")
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Attribute attribute;

    @Column(length = 128,nullable = false)
    private String value;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
