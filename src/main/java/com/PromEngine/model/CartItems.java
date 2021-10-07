package com.PromEngine.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 *	Cart Item details Model class
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class CartItems {
    @JsonProperty("ItemName")
	private String itemName;
    @JsonProperty("Quantity")
	private int quantity;
    @JsonProperty("TotalAmount")
	private double TotalAmount;
    public CartItems(String itemName, int quantity) {
        super();
        this.itemName = itemName;
        this.quantity = quantity;
    }
    
}
