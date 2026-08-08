package com.example.lab7_6733803909_5_sec4.strategy;

import org.springframework.stereotype.Component;

@Component
public class StudentDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10; // 10% discount
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * (1 - DISCOUNT_RATE);
    }
}
