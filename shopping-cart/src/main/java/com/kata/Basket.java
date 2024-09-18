package com.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    protected Map<String, CartItem> itemsMap;

    protected Discount discount;

    public Basket() {
        itemsMap = new HashMap<>();
        discount = Discount.empty();
    }

    public void addItem(String aName, double price) {
        final var item = itemsMap.getOrDefault(aName, CartItem.create(aName, price));
        itemsMap.put(aName, item.increment());
    }

    public void deleteItem(String productName) {
        itemsMap.remove(productName);
    }

    public void applyDiscount(Discount aDiscount) {
        discount = aDiscount;
    }

    public int totalQuantity() {
        return itemsMap.values().stream()
            .map(CartItem::quantity)
            .reduce(0, Integer::sum);
    }

    public double totalPrice() {
        return discount.applyFor(getTotal());
    }

    private Double getTotal() {
        return itemsMap.values().stream()
            .map(CartItem::totalPrice)
            .reduce(0.00, Double::sum);
    }

    public List<CartItem> getItems() {
        return itemsMap.values().stream().toList();
    }

    public Discount getDiscount() {
        return discount;
    }
}
