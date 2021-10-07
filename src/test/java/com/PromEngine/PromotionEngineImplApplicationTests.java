package com.PromEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.PromEngine.controllers.PromEngineController;
import com.PromEngine.model.CartItems;
import com.PromEngine.model.ProductInfo;
import com.PromEngine.repository.ProductRepository;

/**
 * 
 * Component Test cases which touches all the flows of our code
 *
 */
@SpringBootTest
class PromotionEngineImplApplicationTests {
    @Autowired
    PromEngineController promEngineController;
    @Autowired
    ProductRepository productRepository;
    

    public List<CartItems> setCartItems(int product1,int product2,int product3,int product4) {
       List<ProductInfo> Products = productRepository.findAll();
        List<CartItems> cartItems = new ArrayList<>();
        CartItems CartItem = new CartItems(Products.get(0).getItemName(), product1);
        cartItems.add(CartItem);
        CartItem = new CartItems(Products.get(1).getItemName(), product2);
        cartItems.add(CartItem);
        CartItem = new CartItems(Products.get(2).getItemName(), product3);
        cartItems.add(CartItem);
        CartItem = new CartItems(Products.get(3).getItemName(), product4);
        cartItems.add(CartItem);
        return cartItems;

    }

    @Test
    void individualProduct1() {
        List<CartItems> cartItems =this.setCartItems(1,0,0,0);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 50.0", s.getBody());
    }
    @Test
    void individualProduct2() {
        List<CartItems> cartItems =this.setCartItems(0,1,0,0);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 30.0", s.getBody());
    }
    @Test
    void individualProduct3() {
        List<CartItems> cartItems =this.setCartItems(0,0,1,0);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 20.0", s.getBody());
    }
    @Test
    void individualProduct4() {
        List<CartItems> cartItems =this.setCartItems(0,0,0,1);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 15.0", s.getBody());
    }
    
    @Test
    void individualAllProduct() {
        List<CartItems> cartItems =this.setCartItems(1,1,1,1);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 110.0", s.getBody());
    }
    @Test
    void individualProductExceptOne() {
        List<CartItems> cartItems =this.setCartItems(1,1,1,0);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 100.0", s.getBody());
    }
    
    @Test
    void MultipleTypeOfAllProducts() {
        List<CartItems> cartItems =this.setCartItems(5,5,1,0);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 370.0", s.getBody());
    }
    
    @Test
    void MultipleAndComboOfAllProducts() {
        List<CartItems> cartItems =this.setCartItems(3,5,1,1);
        ResponseEntity<String> s = promEngineController.calculateFinalPrice(cartItems);
       assertEquals("The Total Price is: 280.0", s.getBody());
    }

}
