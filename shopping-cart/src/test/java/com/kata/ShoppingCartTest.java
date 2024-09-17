package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShoppingCartTest {

    @Mock
    private Console console;

    private ShoppingCart sut;

    @BeforeEach
    void setUp() {
        var basketPrinter = new BasketPrinter(console);
        final var products = List.of(new Product("x", 1.50));
        var productRepository = new ProductRepository(products);
        final var coupons = List.of(new Discount("PROMO_5", 0.05));
        var discountRepository = new DiscountRepository(coupons);
        sut = new ShoppingCart(productRepository, discountRepository, basketPrinter);
    }

    @Test
    void givenACartWithProductAndACoupon_whenPrinted_shouldProduceTotals() {
        sut.addItem("x");
        sut.addItem("x");
        sut.addItem("y");
        sut.applyDiscount(0.05);
        sut.printShoppingCart();
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Product name | Price with VAT | Quantity");
        inOrder.verify(console).printLine("x | 1.50 £ | 2");
        inOrder.verify(console).printLine("Promotion: 5% off with code PROMO_5");
        inOrder.verify(console).printLine("Total products: 2");
        inOrder.verify(console).printLine("Total price: 2.85 £");
    }
}