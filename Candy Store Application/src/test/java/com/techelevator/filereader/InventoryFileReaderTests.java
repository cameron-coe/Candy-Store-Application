package com.techelevator.filereader;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.items.HardCandy;
import org.junit.*;
import java.util.Scanner;
public class InventoryFileReaderTests {
    private InventoryFileReader target;

    @Before
    public void before_test() {
        target = new InventoryFileReader("null");
    }

    @Test
    public void turns_string_into_candy_object() {
        String candyDataText = "HC|H1|Jolly Cowboy|2.35|T";
        CandyStoreItem expectedOutput = new HardCandy("H1", "Jolly Cowboy",
                true, 100, 235);
        CandyStoreItem input = target.textToCandyObject(candyDataText);
        Assert.assertTrue(expectedOutput.equals(input));
    }





}
