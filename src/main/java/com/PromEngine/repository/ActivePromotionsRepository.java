package com.PromEngine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PromEngine.model.ActivePromotions;

public interface ActivePromotionsRepository  extends JpaRepository<ActivePromotions, Long> {
	public Optional<ActivePromotions> findByPromotionName(String promotionName);
    
}
