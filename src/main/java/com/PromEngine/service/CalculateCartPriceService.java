package com.PromEngine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PromEngine.model.ActivePromotions;
import com.PromEngine.model.CartItems;
import com.PromEngine.model.ProductInfo;
import com.PromEngine.model.PromotionType;
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
            
            if (activePromotions.get().getPromotionType().equals(PromotionType.MultipleQuantityPromotion)) {
                ci.setTotalAmount(this.calculateProductForMultiple(quantity, itemName,
                		activePromotions.get().getPromotionName(), activePromotions.get().getPromotionQuantityNumber()));
            }
    	});
    	double finalPrice = cartItems.stream().mapToDouble(ci -> ci.getTotalAmount()).sum();
    	return finalPrice;
    }
    
    private double calculateProductForMultiple(int quantity, String itemName, String PromotionName,
            int promoOnNumberOfItems) {
    	Optional<ActivePromotions> activePromotion = activePromotionsRepository.findByPromotionName(PromotionName);
        Optional<ProductInfo> product = productRepository.findByItemName(itemName);
        
    	double priceOfItem = 0;
    	return priceOfItem;
    }
}