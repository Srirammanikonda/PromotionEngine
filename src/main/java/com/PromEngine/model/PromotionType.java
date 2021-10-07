package com.PromEngine.model;

/**
 * 
 * Class which differentiates the type of promotion
 *
 */
public enum PromotionType {
    MultipleQuantityPromotion(1), ComboProductPromotion(2);

    private int TypeCode;

    private PromotionType(int typeCode) {
        this.TypeCode = (short) typeCode;
    }

    public int getPromotionType() {
        return TypeCode;
    }
}
