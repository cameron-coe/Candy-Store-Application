package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.view.Menu;

import java.util.Map;

/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * This class should control the logical workflow of the application, but it should do no other
 * work.  It should communicate with the user (System.in and System.out) using the Menu class and ask
 * the CandyStore class to do any work and pass the results between those 2 classes.
 */
public class ApplicationCLI {

	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private Menu menu;
	private CandyStore candyStore;

	public ApplicationCLI(Menu menu) {
		this.menu = menu;
		this.candyStore= new CandyStore();
	}

	/*
	 * Your application starts here
	 */
	public void run() {

		menu.showWelcomeMessage();
		candyStore.getFileNameSetCandyMap(menu.getUserInputFileName());

		while (true) {
			//Main Menu
			String userInput = menu.showMainMenu();

			if (userInput.equalsIgnoreCase("1")){
				//Candy Inventory
				menu.inventoryDisplay(candyStore.getCandyInventory());

			} else if (userInput.equalsIgnoreCase("2")){
				// Make Sale Page
				boolean isShopping = true;
				while (isShopping) {
					int currentBalance = candyStore.getBalanceFromBalanceManager();
					String userInputForMakeSalePage = menu.showMakeSalePage(currentBalance);

					if (userInputForMakeSalePage.equalsIgnoreCase("1")) {
						// Take Money Page
						int amountToTakeInCents = menu.showTakeMoneyPage();
						candyStore.addBalanceToBalanceManager(amountToTakeInCents);

					}else if (userInputForMakeSalePage.equalsIgnoreCase("2")) {
						// Select Product Page
						menu.inventoryDisplay(candyStore.getCandyInventory());
						Map<String, Integer> candyAndAmount = menu.showSelectProductPage(candyStore.getCandyInventory(), candyStore.getBalanceFromBalanceManager());

						if (!candyAndAmount.isEmpty()) {
							for (Map.Entry<String, Integer> currentCandyAndAmount : candyAndAmount.entrySet()) {
								candyStore.buyingCandyById(currentCandyAndAmount.getKey(), currentCandyAndAmount.getValue());
							}
						}

					} else if (userInputForMakeSalePage.equalsIgnoreCase("3")) {
						menu.showsChange(candyStore.getChangeFromBalanceManager());
					}
				}
			}


			/*
			Display the Starting Menu and get the users choice.
			Remember all uses of System.out and System.in should be in the menu
			
			IF the User Choice is Show Inventory,
				THEN show the candy store items for sale
			ELSE IF the User's Choice is Make Sale,
				THEN go to the make sale menu
			ELSE IF the User's Choice is Quit
				THEN break the loop so the application stops
			*/
		}
	}

	/*
	 * This starts the application, but you shouldn't need to change it.  
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		ApplicationCLI cli = new ApplicationCLI(menu);
		cli.run();
	}
}
