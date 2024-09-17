package com.kata;

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
        if (isEmpty(basket)) {
            console.printLine("- | - £ | -");
            return;
        }
        for (var item : basket.getItems())
            printItem(item);
    }

    private void printItem(CartItem item) {
        final var itemLine = format("%s | %.2f £ | %d", item.name(), item.price(), item.quantity());
        console.printLine(itemLine);
    }

    private void printDiscount(Basket basket) {
        if (isEmpty(basket)) {
            console.printLine("Promotion: ");
            return;
        }
        final var discount = basket.getDiscount();
        final var percentage = (int) (discount.percentage() * 100);
        final var discountLine = format("Promotion: %d%% off with code %s", percentage, discount.name());
        console.printLine(discountLine);
    }

    private static boolean isEmpty(Basket basket) {
        return basket.getItems().isEmpty();
    }

    private void printTotals(Basket basket) {
        console.printLine(format("Total products: %d", basket.totalQuantity()));
        console.printLine(format("Total price: %.2f £", basket.totalPrice()));
    }
}
