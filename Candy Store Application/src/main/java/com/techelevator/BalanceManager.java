package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class BalanceManager {
    private int currentBalance;
    private static final int [] CHANGE_BY_DENOMINATIONS_IN_CENTS = new int[] {2000, 1000, 500, 100, 25, 10, 5};

    public BalanceManager() {
        this.currentBalance = 0;
    }


    public int getCurrentBalance() {
        return currentBalance;
    }

    public Map<Integer, Integer> getChangeByDenominations() {
        int currentBalanceInCents = currentBalance;
        Map<Integer, Integer> outputChange = new HashMap<>();
        for (int i = 0; i < CHANGE_BY_DENOMINATIONS_IN_CENTS.length; i++) {
            outputChange.put(CHANGE_BY_DENOMINATIONS_IN_CENTS[i], currentBalanceInCents / CHANGE_BY_DENOMINATIONS_IN_CENTS[i]);
            currentBalanceInCents = currentBalanceInCents % CHANGE_BY_DENOMINATIONS_IN_CENTS[i];
        }
        return outputChange;
    }

    public void setCurrentBalance(int currentBalanceInCents) {
        this.currentBalance = currentBalanceInCents;
    }
}
