package com.kata;

import java.util.List;
import java.util.Optional;

public class DiscountRepository {

    private List<Discount> coupons;

    public DiscountRepository(List<Discount> discounts) {
        this.coupons = discounts;
    }

    public Optional<Discount> get(double discountPercentage) {
        return coupons.stream()
            .filter(discount -> discount.percentage() == discountPercentage)
            .findFirst();
    }
}
