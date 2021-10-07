package com.PromEngine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PromEngine.model.ActivePromotions;
import com.PromEngine.model.CartItems;
import com.PromEngine.model.ProductInfo;
import com.PromEngine.repository.ActivePromotionsRepository;
import com.PromEngine.repository.ProductRepository;


@Service
@Transactional
public class CalculateCartPriceService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ActivePromotionsRepository activePromotionsRepository;
    
    public double calculateCartPrice(List<CartItems> cartItems) {
    	cartItems.forEach(ci -> {
            String itemName = ci.getItemName();
            int quantity = ci.getQuantity();
            Optional<ProductInfo> product = productRepository.findByItemName(itemName);
            
            Optional<ActivePromotions> activePromotions = activePromotionsRepository
                    .findById(product.get().getPromoCode());
    	});
    	double finalPrice = 0;
    	return finalPrice;
    }
}