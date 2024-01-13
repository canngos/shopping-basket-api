package com.canngos.shoppingbasketservice.repository;

import com.canngos.shoppingbasketservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
