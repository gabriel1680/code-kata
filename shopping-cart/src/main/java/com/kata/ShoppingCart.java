package com.kata;

import java.util.function.Consumer;

public class ShoppingCart {

    private final Basket basket;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    private final BasketPrinter basketPrinter;

    public ShoppingCart(
        ProductRepository productRepository,
        DiscountRepository discountRepository,
        BasketPrinter basketPrinter
    ) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
        this.basketPrinter = basketPrinter;
        basket = new Basket();
    }

    public void addItem(String productName) {
        executeWhenProductExists(productName, product ->
            basket.addItem(product.name(), product.price()));
    }

    public void deleteItem(String productName) {
        executeWhenProductExists(productName, product ->
            basket.deleteItem(product.name()));
    }

    private void executeWhenProductExists(String productName, Consumer<Product> consumer) {
        productRepository.get(productName).ifPresent(consumer);
    }

    public void applyDiscount(double discountPercentage) {
        discountRepository.get(discountPercentage).ifPresent(basket::applyDiscount);
    }

    public void printShoppingCart() {
        basketPrinter.print(basket);
    }
}
