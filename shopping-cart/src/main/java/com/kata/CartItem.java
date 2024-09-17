package com.kata;

public record CartItem(String name, double price, int quantity) {

    public static CartItem create(String productName, double price) {
        return new CartItem(productName, price, 0);
    }

    public CartItem increment() {
        return new CartItem(name, price, quantity + 1);
    }

    public double totalPrice() {
        return price * quantity;
    }
}
