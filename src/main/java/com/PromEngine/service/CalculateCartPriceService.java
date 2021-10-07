package com.PromEngine.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        finalPrice = finalPrice + calculateComboProductsPricing(cartItems);
    	return finalPrice;
    }
    
    private double calculateProductForMultiple(int quantity, String itemName, String PromotionName,
            int promoOnNumberOfItems) {
        Optional<ActivePromotions> activePromotion = activePromotionsRepository.findByPromotionName(PromotionName);
        Optional<ProductInfo> product = productRepository.findByItemName(itemName);
        double priceOfItem = 0;

        if (activePromotion.isPresent()) {
            int promoAppliedQuantity = quantity / promoOnNumberOfItems;
            int nonPromoQuantity = quantity % promoOnNumberOfItems;

            if (nonPromoQuantity == 0) {
                priceOfItem = activePromotion.get().getEffectivePrice() * promoAppliedQuantity;
            } else {
                priceOfItem = activePromotion.get().getEffectivePrice() * promoAppliedQuantity;
                priceOfItem = priceOfItem + (nonPromoQuantity * product.get().getUnitPrice());
            }
        }
        return priceOfItem;
    }
    
    private double calculateComboProductsPricing(List<CartItems> cartItems) {
    	List<ActivePromotions> activePromotions = activePromotionsRepository.findAll();
    	List<ActivePromotions> comboPromos = activePromotions.stream()
                .filter(ap -> ap.getPromotionType().equals(PromotionType.ComboProductPromotion))
                .collect(Collectors.toList());
        double priceOfItem = 0;
        return priceOfItem;
    }
}