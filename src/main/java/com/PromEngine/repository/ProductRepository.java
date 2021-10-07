package com.PromEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PromEngine.model.ProductInfo;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
    

}