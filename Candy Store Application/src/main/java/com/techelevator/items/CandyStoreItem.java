package com.techelevator.items;

import java.util.Objects;

/*
    This represents a single catering item in the system

    This is an abstract class that should be used as a superclass for the items.
 */
public abstract class CandyStoreItem {
    private String id;
    private String name;
    private boolean isIndividuallyWrapped;
    private int quantity;
    private int priceInCents;

    public CandyStoreItem(String id, String name, boolean isIndividuallyWrapped, int quantity, int priceInCents) {
        this.id = id;
        this.name = name;
        this.isIndividuallyWrapped = isIndividuallyWrapped;
        this.quantity = quantity;
        this.priceInCents = priceInCents;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIndividuallyWrapped() {
        return isIndividuallyWrapped;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void removesQuantity (int amountToRemove) {
        this.quantity -= amountToRemove;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandyStoreItem that = (CandyStoreItem) o;
        return isIndividuallyWrapped == that.isIndividuallyWrapped
                && quantity == that.quantity && Double.compare(that.priceInCents, priceInCents) == 0
                && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }



}
