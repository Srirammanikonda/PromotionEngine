package com.PromEngine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PromEngine.model.ProductInfo;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
	public Optional<ProductInfo> findByItemName(String itemName);
	
	public List<ProductInfo> findByPromoCode(Long promoCode);
}