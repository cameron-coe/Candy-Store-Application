package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CandyStoreItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {

    private String inventoryFileName;

    private Map<String, CandyStoreItem> candyInventory = new HashMap<>();
    private BalanceManager balanceManager;
    private Map<CandyStoreItem, Integer> shoppingCart;
    public CandyStore () {
        balanceManager = new BalanceManager();
        shoppingCart = new HashMap<>();
    }

    public Map<String, CandyStoreItem> getCandyInventory() {
        return candyInventory;
    }

    public void getFileNameSetCandyMap (String fileName) {
        this.inventoryFileName = fileName;
        InventoryFileReader items = new InventoryFileReader(inventoryFileName);
        this.candyInventory = putCandyListIntoMap(items.getListOfCandyItems());
    }

    public Map<String, CandyStoreItem> putCandyListIntoMap(List<CandyStoreItem> candyList) {
        Map<String, CandyStoreItem> candyMap = new HashMap<>();
        if (candyList != null) {
            for (CandyStoreItem currentCandy : candyList) {
                candyMap.put(currentCandy.getId(), currentCandy);
            }
        }
        return candyMap;

    }


    //Balance Manager Methods

    public int getBalanceFromBalanceManager () {
        return balanceManager.getCurrentBalance();
    }

    public Map<Integer, Integer> getChangeFromBalanceManager () {
        return balanceManager.getChangeByDenominations();
    }



    public void addBalanceToBalanceManager(int amount) {
        int newAmount = balanceManager.getCurrentBalance() + amount;
        if (newAmount <= 100000 && amount <= 10000){
            balanceManager.setCurrentBalance(newAmount);
        }

    }

    public void subtractBalanceFromBalanceManager(int amount) {
        int newAmount = balanceManager.getCurrentBalance() - amount;
        if (newAmount >= 0) {
            balanceManager.setCurrentBalance(newAmount);
        }
    }

    public void clearBalanceInBalanceManager() {
        balanceManager.setCurrentBalance(0);
    }

    //Shopping Cart Methods

    public Map<CandyStoreItem, Integer> getShoppingCart () {
        return this.shoppingCart;
    }

    public void buyingCandyById (String candyId, int amount) {
        addCandyToCart(candyInventory.get(candyId),amount);
        candyInventory.get(candyId).removesQuantity(amount);
        int totalAmountToRemove = candyInventory.get(candyId).getPriceInCents() * amount;
        subtractBalanceFromBalanceManager(totalAmountToRemove);
    }

    public void addCandyToCart (CandyStoreItem candy, int amount) {

        if (shoppingCart.containsKey(candy)) {
            shoppingCart.put(candy, shoppingCart.get(candy) + amount);
        } else {
            shoppingCart.put(candy, amount);
        }


    }





}
