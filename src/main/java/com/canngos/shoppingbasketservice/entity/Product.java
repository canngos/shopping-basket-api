package com.canngos.shoppingbasketservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type = ProductType.OTHER;
    @Column(nullable = false)
    private Integer quantity;
}
