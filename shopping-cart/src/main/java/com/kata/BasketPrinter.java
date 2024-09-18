package com.kata;

import static java.lang.String.format;

public class BasketPrinter {

    private final Console console;

    public BasketPrinter(Console console) {
        this.console = console;
    }

    public void print(Basket basket) {
        console.printLine("Product name | Price with VAT | Quantity");
        if (isEmpty(basket)) {
            printEmpty(basket);
        } else {
            printItems(basket);
            printDiscount(basket);
        }
        printTotals(basket);
    }

    private static boolean isEmpty(Basket basket) {
        return basket.getItems().isEmpty();
    }

    private void printEmpty(Basket basket) {
        console.printLine("- | - £ | -");
        console.printLine("Promotion: ");
    }

    private void printItems(Basket basket) {
        for (var item : basket.getItems())
            printItem(item);
    }

    private void printItem(CartItem item) {
        console.printLine(
            format("%s | %.2f £ | %d", item.name(), item.price(), item.quantity())
        );
    }

    private void printDiscount(Basket basket) {
        console.printLine(
            format("Promotion: %d%% off with code %s", getDiscountPercentage(basket), getDiscountName(basket))
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
