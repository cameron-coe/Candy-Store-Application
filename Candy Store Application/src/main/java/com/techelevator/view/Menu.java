package com.techelevator.view;

import com.techelevator.items.CandyStoreItem;

import java.util.*;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the candy store.
 */
public class Menu {
	
	private static final Scanner in = new Scanner(System.in);

	public void showWelcomeMessage() {
		System.out.println("***************************");
		System.out.println("**     Silver Shamrock   **");
		System.out.println("**      Candy Company    **");
		System.out.println("***************************");
		System.out.println();
	}

	public String getUserInputFileName () {
		System.out.println("Put in the file destination for inventory: ");
		String fileName = in.nextLine();
		return fileName;
	}

	public String showMainMenu() {
		System.out.println("(1) Show Inventory");
		System.out.println("(2) Make Sale");
		System.out.println("(3) Quit");

		return in.nextLine();


	}
	public String formattedAmount (int amountInCents) {
		double amountInDollars = (double) amountInCents / 100;
		return String.format("$%1.2f" , amountInDollars);

	}

	public void inventoryDisplay(Map<String, CandyStoreItem> candyMap) {
		Map<String, CandyStoreItem> sortedMap = new TreeMap<>(candyMap);

		System.out.println("Id   Name                 Wrapper   Qty        Price");
		for (Map.Entry<String, CandyStoreItem> currentItem : sortedMap.entrySet() ){
			displaySortedItems(currentItem.getKey(),
					currentItem.getValue().getName(),
					currentItem.getValue().isIndividuallyWrapped(),
					currentItem.getValue().getQuantity(),
					currentItem.getValue().getPriceInCents());
		}

		System.out.println();
	}

	public void displaySortedItems (String id, String name, boolean isIndividuallyWrapped, int quantity, int price) {
		String output = "";


		String column1 = id;
		String column2 = name;

		String column3 = "";
		if (isIndividuallyWrapped) {
			column3 = "Y";
		} else {
			column3 = "N";
		}

		String column4 = "";
		if (quantity == 0) {
			column4 = "SOLD OUT";
		} else {
			column4 = "" + quantity;
		}

		String column5 = formattedAmount(price);

		output += (String.format("%-4s %-20s %-9s %-10s %-10s", column1, column2, column3, column4, column5));

		System.out.println(output);
	}

	public String showMakeSalePage(int amountInBalance) {
		System.out.println("(1) Take Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Sale");
		System.out.println("Current Customer Balance: " + formattedAmount(amountInBalance));
		return in.nextLine();
	}

	public int showTakeMoneyPage() {
		System.out.println("Type Amount to Add To Balance In Whole Dollar Amounts: ");
		boolean isInputDouble = false;
		int userInput = 0;
		while (!isInputDouble) {
			try {
				userInput = Integer.parseInt(in.nextLine().replace("$", "")) * 100;
				isInputDouble = true;
			} catch (NumberFormatException e) {
				System.out.println("Input Could Not Be Read, Please Try Again");
			}
		}
		return userInput;

	}

	public Map<String, Integer> showSelectProductPage(Map<String, CandyStoreItem> candyInventory, double customerBalance) {
		System.out.println("Put in the candy Id you want to purchase:");
		String candyId = in.nextLine().toUpperCase();
		Map<String, Integer> outputCandyAndAmount = new HashMap<>();

		if (candyInventory.containsKey(candyId)) {
			try {
				System.out.println("Put in amount you want to purchase:");
				int candyAmount = Integer.parseInt(in.nextLine());
				// output the CSI and int
				if (canPurchaseBeMade(customerBalance,candyAmount,
						candyInventory.get(candyId).getQuantity(),
						candyInventory.get(candyId).getPriceInCents())) {
					outputCandyAndAmount.put(candyId, candyAmount);
				}


			} catch (NumberFormatException e) {
				System.out.println("Invalid Candy Amount");
			}
		}else {
			System.out.println("Id Can Not Be Found");
		}
		return outputCandyAndAmount;
	}

	public boolean canPurchaseBeMade (double currentBalance, int quantity, int candyInStock, double pricePerUnit) {
		boolean purchaseCanBeMade = true;
		if (candyInStock == 0){
			purchaseCanBeMade = false;
			System.out.println("Candy Is Out Of Stock");
		}else if (quantity > candyInStock) {
			purchaseCanBeMade = false;
			System.out.println("Not Enough Candy In Stock");
		}else if (currentBalance < (quantity * pricePerUnit)){
			purchaseCanBeMade = false;
			System.out.println("Not Enough Funds In Balance");
		}

		return purchaseCanBeMade;
	}

	public void showShoppingCart(Map<CandyStoreItem, Integer> shoppingCart) {
		Map<String, CandyStoreItem> candySortedById = new TreeMap<>();
		for (Map.Entry<CandyStoreItem, Integer> currentItem : shoppingCart.entrySet() ){
			candySortedById.put(currentItem.getKey().getId(), currentItem.getKey());
		}

		System.out.println("Id   Name                 Wrapper   Qty        Amount Purchased");
		for (Map.Entry<String, CandyStoreItem> currentItem : candySortedById.entrySet() ){
			displaySortedItems(currentItem.getKey(),
					currentItem.getValue().getName(),
					currentItem.getValue().isIndividuallyWrapped(),
					shoppingCart.get(currentItem.getValue()),
					currentItem.getValue().getPriceInCents());
		}
		System.out.println();
		System.out.println();
	}




	public void showCompleteSalePage () {

	}
	public void showsChange (Map<Integer, Integer> changeByDenominations) {
		int changeAmount = 0;
		String listOfBillsAndCoins = "";
		if (changeByDenominations.get(2000) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(2000) + ") ";
			listOfBillsAndCoins += "Twenties, ";
			changeAmount += 2000 * changeByDenominations.get(2000);
		}
		if (changeByDenominations.get(1000) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(1000) + ") ";
			listOfBillsAndCoins += "Tens, ";
			changeAmount += 1000 * changeByDenominations.get(1000);
		}

		if (changeByDenominations.get(500) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(500) + ") ";
			listOfBillsAndCoins += "Fives, ";
			changeAmount += 500 * changeByDenominations.get(500);
		}
		if (changeByDenominations.get(100) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(100) + ") ";
			listOfBillsAndCoins += "Ones, ";
			changeAmount += 100 * changeByDenominations.get(100);
		}
		if (changeByDenominations.get(25) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(25) + ") ";
			listOfBillsAndCoins += "Quarters, ";
			changeAmount += 25 * changeByDenominations.get(25);
		}
		if (changeByDenominations.get(10) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(10) + ") ";
			listOfBillsAndCoins += "Dimes, ";
			changeAmount += 10 * changeByDenominations.get(10);
		}
		if (changeByDenominations.get(5) > 0) {
			listOfBillsAndCoins += "(" + changeByDenominations.get(5) + ") ";
			listOfBillsAndCoins += "Nickles, ";
			changeAmount += 5 * changeByDenominations.get(5);
		}
		System.out.println("Change: " + formattedAmount(changeAmount));
		System.out.println(listOfBillsAndCoins.substring(0, listOfBillsAndCoins.length()-2));

	}


}
