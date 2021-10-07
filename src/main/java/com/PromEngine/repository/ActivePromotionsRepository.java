package com.PromEngine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PromEngine.model.ActivePromotions;

public interface ActivePromotionsRepository  extends JpaRepository<ActivePromotions, Long> {
    
}
