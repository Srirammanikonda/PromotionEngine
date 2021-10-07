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
@Table(name="ActivePromotions")
public class ActivePromotions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long promo_code;
	
	@Column(name="PromotionName")
	private String promotionName;
	
	@Column(name="EffectivePrice")
	private int effectivePrice;
	
    @Column(name="PromoQuantityNum")
	private int promotionQuantityNumber;
	
}
