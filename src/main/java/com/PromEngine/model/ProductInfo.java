package com.PromEngine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ProductInfo")
public class ProductInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="ItemName")
	private String itemName;
	
	@Column(name="UnitPrice")
	private Long unitPrice;
	
	@Column(name="PromoCode")
	private long promoCode;
}