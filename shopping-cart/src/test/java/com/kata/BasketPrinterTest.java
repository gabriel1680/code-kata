package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BasketPrinterTest {

    private Basket basket;

    @Mock
    private Console console;

    @InjectMocks
    private BasketPrinter sut;

    @BeforeEach
    void setUp() {
        basket = new Basket();
    }

    @Test
    void printEmptyBasket() {
        sut.print(basket);
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Product name | Price with VAT | Quantity");
        inOrder.verify(console).printLine("- | - £ | -");
        inOrder.verify(console).printLine("Promotion: ");
        inOrder.verify(console).printLine("Total products: 0");
        inOrder.verify(console).printLine("Total price: 0.00 £");
    }

    @Test
    void printFilledBasket() {
        basket.addItem("x", 3.00);
        basket.addItem("x", 3.00);
        basket.addItem("y", 4.00);
        basket.applyDiscount(new Discount("z", 0.40));
        sut.print(basket);
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Product name | Price with VAT | Quantity");
        inOrder.verify(console).printLine("x | 3.00 £ | 2");
        inOrder.verify(console).printLine("y | 4.00 £ | 1");
        inOrder.verify(console).printLine("Promotion: 40% off with code z");
        inOrder.verify(console).printLine("Total products: 3");
        inOrder.verify(console).printLine("Total price: 6.00 £");
    }
}