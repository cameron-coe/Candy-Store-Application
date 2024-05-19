package com.techelevator;

import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class BalanceManagerTests {
    private BalanceManager target;

    @Before
    public void before_tests () {
        target = new BalanceManager();
    }

    @Test
    public void gives_right_change () {
        target.setCurrentBalance(3190);
        Map<Integer, Integer> input = target.getChangeByDenominations();

        Map<Integer, Integer> expectedOutput = new HashMap<>();
        expectedOutput.put(2000, 1);
        expectedOutput.put(1000, 1);
        expectedOutput.put(500, 0);
        expectedOutput.put(100, 1);
        expectedOutput.put(25, 3);
        expectedOutput.put(10, 1);
        expectedOutput.put(5, 1);

        Assert.assertEquals(expectedOutput, input);

    }
}
