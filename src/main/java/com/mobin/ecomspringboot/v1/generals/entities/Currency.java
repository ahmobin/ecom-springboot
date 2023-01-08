package com.mobin.ecomspringboot.v1.generals.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(length = 32, nullable = false, unique = true)
    @JsonIgnore
    private String currency;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    private List<Product> products;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

}
