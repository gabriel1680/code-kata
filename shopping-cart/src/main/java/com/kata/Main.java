package com.kata;

import java.util.List;

public class Main {

    private static final List<Product> products =
            List.of(new Product("x", 1.50), new Product("y", 2.35), new Product("z", 3.00));
    private static final List<Discount> discounts =
            List.of(new Discount("PROMO_5", 0.05));

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository(products);
        DiscountRepository discountRepository = new DiscountRepository(discounts);
        BasketPrinter basketPrinter = new BasketPrinter(new Console());
        ShoppingCart shoppingCart = new ShoppingCart(
            productRepository,
            discountRepository,
            basketPrinter
        );
        shoppingCart.addItem("x");
        shoppingCart.addItem("z");
        shoppingCart.addItem("x");
        shoppingCart.addItem("y");
        shoppingCart.deleteItem("z");
        shoppingCart.applyDiscount(0.05);
        shoppingCart.applyDiscount(0.25);
        shoppingCart.printShoppingCart();
    }
}
