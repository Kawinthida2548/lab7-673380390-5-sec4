package com.example.lab7_6733803909_5_sec4.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountContext {

    private final DiscountStrategy noDiscountStrategy;
    private final DiscountStrategy studentDiscountStrategy;
    private final DiscountStrategy seasonalSaleStrategy;

    public DiscountContext(NoDiscountStrategy noDiscountStrategy,
                          StudentDiscountStrategy studentDiscountStrategy,
                          SeasonalSaleStrategy seasonalSaleStrategy) {
        this.noDiscountStrategy = noDiscountStrategy;
        this.studentDiscountStrategy = studentDiscountStrategy;
        this.seasonalSaleStrategy = seasonalSaleStrategy;
    }

    public double calculateFinalPrice(String discountType, double originalPrice) {
        DiscountStrategy strategy = selectStrategy(discountType);
        return strategy.calculatePrice(originalPrice);
    }

    private DiscountStrategy selectStrategy(String discountType) {
        if (discountType == null) {
            return noDiscountStrategy;
        }
        switch (discountType.toUpperCase()) {
            case "STUDENT":
                return studentDiscountStrategy;
            case "SEASONAL":
                return seasonalSaleStrategy;
            case "NONE":
            default:
                return noDiscountStrategy;
        }
    }
}
