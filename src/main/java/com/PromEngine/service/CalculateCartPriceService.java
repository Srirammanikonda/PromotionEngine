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

/**
 * 
 * This method receives the request from the controller and helps in calculating the final cart price.
 *
 */

@Service
@Transactional
public class CalculateCartPriceService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ActivePromotionsRepository activePromotionsRepository;
    
    /**
     * Method accepts list of cart items and processes the request to calculate cart price
     * 
     * @param List<CartItems>
     * @return
     */
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
    
    /**
     * Method which calculates price for Multi-item carts
     * 
     * @param quantity
     * @param itemName
     * @param PromotionName
     * @param promoOnNumberOfItems
     * @return
     */
     
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
    
    /**
     * Method which calculates price for Combination of items in cart
     * 
     * @param cartItems
     * @return
     */
    private double calculateComboProductsPricing(List<CartItems> cartItems) {
    	List<ActivePromotions> activePromotions = activePromotionsRepository.findAll();
    	List<ActivePromotions> comboPromos = activePromotions.stream()
                .filter(ap -> ap.getPromotionType().equals(PromotionType.ComboProductPromotion))
                .collect(Collectors.toList());
        double priceOfItem = 0;
        
        for(ActivePromotions activePromotion:comboPromos) {
	            
	            List<ProductInfo> products = productRepository.findByPromoCode(activePromotion.getPromo_code());
	            List<CartItems> ItemC = cartItems.stream().filter(ci -> ci.getItemName().equals(products.get(0).getItemName()))
	                    .collect(Collectors.toList());
	            List<CartItems> ItemD = cartItems.stream().filter(ci -> ci.getItemName().equals(products.get(1).getItemName()))
	                    .collect(Collectors.toList());
	           
	            if (!ItemC.isEmpty() && !ItemD.isEmpty()) {
	                if (ItemC.get(0).getQuantity() == ItemD.get(0).getQuantity()) {
	                    priceOfItem = ItemC.get(0).getQuantity() * activePromotion.getEffectivePrice();
	                } else if (ItemC.get(0).getQuantity() < ItemD.get(0).getQuantity()) {
	                    int remainingOfD = ItemD.get(0).getQuantity() - ItemC.get(0).getQuantity();
	                    priceOfItem = ItemC.get(0).getQuantity() * activePromotion.getEffectivePrice();
	                    priceOfItem = priceOfItem + (products.get(1).getUnitPrice() * remainingOfD);
	                } else if (ItemD.get(0).getQuantity() < ItemC.get(0).getQuantity()) {
	                    int remainingOfC = ItemC.get(0).getQuantity() - ItemD.get(0).getQuantity();
	                    priceOfItem = ItemD.get(0).getQuantity() * activePromotion.getEffectivePrice();
	                    priceOfItem = priceOfItem + (products.get(0).getUnitPrice() * remainingOfC);
	                }
	            } else if (!ItemC.isEmpty()) {
	                priceOfItem = ItemC.get(0).getQuantity() * (products.get(0).getUnitPrice());
	            } else if (!ItemD.isEmpty()) {
	                priceOfItem = ItemD.get(0).getQuantity() * (products.get(1).getUnitPrice());
	            }
	        }
        return priceOfItem;
    }
}