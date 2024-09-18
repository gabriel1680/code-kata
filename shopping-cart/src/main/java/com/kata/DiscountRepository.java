package com.kata;

import java.util.List;
import java.util.Optional;

public class DiscountRepository {

    private final List<Discount> discounts;

    public DiscountRepository(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Optional<Discount> get(double discountPercentage) {
        return discounts.stream()
            .filter(discount -> discount.percentage() == discountPercentage)
            .findFirst();
    }
}
