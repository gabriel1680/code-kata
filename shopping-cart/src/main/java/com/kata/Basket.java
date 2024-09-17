package com.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    protected Map<String, CartItem> itemMap;

    protected Discount discount;

    public Basket() {
        itemMap = new HashMap<>();
        discount = Discount.empty();
    }

    public void addItem(String aName, double price) {
        final var item = itemMap.getOrDefault(aName, CartItem.create(aName, price));
        itemMap.put(aName, item.increment());
    }

    public void deleteItem(String productName) {
        itemMap.remove(productName);
    }

    public void applyDiscount(Discount aDiscount) {
        discount = aDiscount;
    }

    public int totalQuantity() {
        return itemMap.values().stream()
            .map(CartItem::quantity)
            .reduce(0, Integer::sum);
    }

    public double totalPrice() {
        final var total = itemMap.values().stream()
            .map(CartItem::totalPrice)
            .reduce(0.00, Double::sum);
        return discount.of(total);
    }

    public List<CartItem> getItems() {
        return itemMap.values().stream().toList();
    }

    public Discount getDiscount() {
        return discount;
    }
}
