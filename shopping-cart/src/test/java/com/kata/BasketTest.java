package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    private Basket sut;

    @BeforeEach
    void setUp() {
        sut = new Basket();
    }

    private void assertBasket(int quantity, double price) {
        assertEquals(quantity, sut.totalQuantity());
        assertEquals(price, sut.totalPrice());
    }

    @Test
    void newBasket_emptyItems() {
        assertBasket(0, 0.00);
    }

    @Test
    void addItem_shouldIncreaseItems() {
        sut.addItem("x", 1.50);
        assertBasket(1, 1.50);
    }

    @Test
    void addSameItem_shouldIncreaseItemQuantityAndPrice() {
        sut.addItem("x", 1.50);
        sut.addItem("x", 1.50);
        assertBasket(2, 3.00);
    }

    @Test
    void deleteItem_whenExists() {
        sut.addItem("x", 1.50);
        sut.deleteItem("x");
        assertBasket(0, 0.00);
    }

    @Test
    void deleteItem_whenDoNotExists() {
        sut.deleteItem("x");
        assertBasket(0, 0.00);
    }

    @Test
    void applyDiscount() {
        sut.addItem("x", 1.50);
        sut.addItem("x", 1.50);
        sut.applyDiscount(0.10);
        assertBasket(2, 2.70);
    }
}