package com.techelevator;

import com.techelevator.CandyStore;
import com.techelevator.items.CandyStoreItem;
import com.techelevator.items.Licorice;
import com.techelevator.items.Sours;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandyStoreTests {

    private CandyStore target;


    @Before
    public void before_test() {
        target = new CandyStore();
    }

    @Test
    public void turns_list_into_map() {
        List<CandyStoreItem> listOfItems = new ArrayList<>();
        listOfItems.add(new Licorice("01", "candy1", true, 100, 50));
        listOfItems.add(new Licorice("02", "candy2", true, 100, 100));
        listOfItems.add(new Licorice("03", "candy3", false, 100, 250));

        Map<String, CandyStoreItem> expectedOutput = new HashMap<>();
        expectedOutput.put("01", listOfItems.get(0));
        expectedOutput.put("02", listOfItems.get(1));
        expectedOutput.put("03", listOfItems.get(2));

        Assert.assertEquals(expectedOutput, target.putCandyListIntoMap(listOfItems));

    }

    @Test
    public void turns_empty_list_into_empty_map() {
        List<CandyStoreItem> listOfItems = new ArrayList<>();
        Map<String, CandyStoreItem> expectedOutput = new HashMap<>();

        Assert.assertEquals(expectedOutput, target.putCandyListIntoMap(listOfItems));
    }

    @Test
    public void turns_null_list_into_empty_map() {
        Map<String, CandyStoreItem> expectedOutput = new HashMap<>();

        Assert.assertEquals(expectedOutput, target.putCandyListIntoMap(null));
    }

    @Test
    public void adds_amount_to_balance_manager() {
        target.addBalanceToBalanceManager(50);
        Assert.assertEquals(50, target.getBalanceFromBalanceManager(),0.009);
    }

    @Test
    public void does_not_add_amount_over_10000_cents_to_balance_manager() {
        target.addBalanceToBalanceManager(10001);
        Assert.assertEquals(0, target.getBalanceFromBalanceManager(),0.009);
    }

    @Test
    public void total_balance_does_not_go_over_100000_cents () {
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(10000);
        target.addBalanceToBalanceManager(1);
        Assert.assertEquals(100000, target.getBalanceFromBalanceManager(), 0.009);
    }

    @Test
    public void subtract_amount_from_balance_manager() {
        target.addBalanceToBalanceManager(50);
        target.subtractBalanceFromBalanceManager(50);
        Assert.assertEquals(0, target.getBalanceFromBalanceManager(),0.009);
    }

    @Test
    public void total_balance_does_not_go_below_zero_dollars() {
        target.addBalanceToBalanceManager(50);
        target.subtractBalanceFromBalanceManager(51);
        Assert.assertEquals(50, target.getBalanceFromBalanceManager(),0.009);
    }

    @Test
    public void adds_candy_to_empty_cart () {
        CandyStoreItem candy1 = new Sours("A", "B", false, 12, 5);
        target.addCandyToCart(candy1, 5);
        Map<CandyStoreItem, Integer> input = target.getShoppingCart();

        Map<CandyStoreItem, Integer> expectedOutput = new HashMap<>();
        expectedOutput.put(candy1, 5);
        Assert.assertEquals(expectedOutput, input);
    }

    @Test
    public void adds_multiple_amounts_of_the_same_candy () {
        CandyStoreItem candy1 = new Sours("A", "B", false, 12, 5);
        target.addCandyToCart(candy1, 5);
        target.addCandyToCart(candy1, 10);
        Map<CandyStoreItem, Integer> input = target.getShoppingCart();

        Map<CandyStoreItem, Integer> expectedOutput = new HashMap<>();
        expectedOutput.put(candy1, 15);
        Assert.assertEquals(expectedOutput, input);
    }

    @Test
    public void adds_different_candies_to_cart () {
        CandyStoreItem candy1 = new Sours("A", "B", false, 12, 5);
        CandyStoreItem candy2 = new Licorice("C", "D", false, 12, 5);
        target.addCandyToCart(candy1, 5);
        target.addCandyToCart(candy2, 5);
        Map<CandyStoreItem, Integer> input = target.getShoppingCart();

        Map<CandyStoreItem, Integer> expectedOutput = new HashMap<>();
        expectedOutput.put(candy1, 5);
        expectedOutput.put(candy2, 5);
        Assert.assertEquals(expectedOutput, input);
    }






}
