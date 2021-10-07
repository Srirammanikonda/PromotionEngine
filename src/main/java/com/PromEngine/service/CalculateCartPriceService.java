package com.PromEngine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PromEngine.model.CartItems;


@Service
@Transactional
public class CalculateCartPriceService {

    public double calculateCartPrice(List<CartItems> cartItems) {
    	double finalPrice = 0;
    	return finalPrice;
    }
}