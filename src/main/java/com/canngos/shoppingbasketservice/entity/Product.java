package com.canngos.shoppingbasketservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type = ProductType.OTHER;
    @Column(nullable = false)
    private int quantity;
}
