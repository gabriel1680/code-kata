package com.kata;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public Optional<Product> get(String productName) {
        return products.stream()
            .filter(p -> p.name().equals(productName))
            .findFirst();
    }
}
