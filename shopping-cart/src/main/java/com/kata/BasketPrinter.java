package com.kata;

import java.util.function.Consumer;

import static java.lang.String.format;

public class BasketPrinter {

    private final Console console;

    public BasketPrinter(Console console) {
        this.console = console;
    }

    public void print(Basket basket) {
        console.printLine("Product name | Price with VAT | Quantity");
        printItems(basket);
        printDiscount(basket);
        printTotals(basket);
    }

    private void printItems(Basket basket) {
        doWhen(basket,
            () -> console.printLine("- | - £ | -"),
            b -> b.getItems().forEach(this::printItem));
    }

    private void printDiscount(Basket basket) {
        doWhen(basket,
            () -> console.printLine("Promotion: "),
            b -> console.printLine(format("Promotion: %d%% off with code %s", getDiscountPercentage(b), getDiscountName(b))));
    }

    private void doWhen(Basket basket, Runnable isEmpty, Consumer<Basket> isNotEmpty) {
        if (isEmpty(basket))
            isEmpty.run();
        else
            isNotEmpty.accept(basket);
    }

    private static boolean isEmpty(Basket basket) {
        return basket.getItems().isEmpty();
    }

    private void printItem(CartItem item) {
        console.printLine(
            format("%s | %.2f £ | %d", item.name(), item.price(), item.quantity())
        );
    }

    private static String getDiscountName(Basket basket) {
        return basket.getDiscount().name();
    }

    private static int getDiscountPercentage(Basket basket) {
        return (int) (basket.getDiscount().percentage() * 100);
    }

    private void printTotals(Basket basket) {
        console.printLine(format("Total products: %d", basket.totalQuantity()));
        console.printLine(format("Total price: %.2f £", basket.totalPrice()));
    }
}
