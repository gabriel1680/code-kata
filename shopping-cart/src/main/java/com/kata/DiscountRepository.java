package com.kata;

import java.util.List;
import java.util.Optional;

public class DiscountRepository {

    private List<Coupon> coupons;

    public DiscountRepository(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Optional<Coupon> get(double discountPercentage) {
        return coupons.stream()
            .filter(coupon -> coupon.discountPercentage() == discountPercentage)
            .findFirst();
    }
}
