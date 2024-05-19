package com.techelevator.filereader;

import com.techelevator.CandyStore;
import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private static final int STARTING_QUANTITY = 100;
    private String inventoryFileName;
    private File inventoryFile;
    private List<CandyStoreItem> listOfCandyItems = new ArrayList<>();

    public InventoryFileReader(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;
        this.inventoryFile =  new File(inventoryFileName);
        this.fileReader();
    }


    // Getter
    public List<CandyStoreItem> getListOfCandyItems() {
        return listOfCandyItems;
    }

    // Methods
    public CandyStoreItem textToCandyObject (String candyData) {
        String[] candyDataArray =  candyData.split("\\|");
        String id = candyDataArray[1];
        String name = candyDataArray[2];
        int priceInCents = Integer.parseInt(candyDataArray[3].replaceAll("\\.", ""));
        boolean isIndividuallyWrapped = candyDataArray[4].equalsIgnoreCase("T");


        if (candyDataArray[0].equalsIgnoreCase("CH")) {
            return new Chocolates(id, name, isIndividuallyWrapped, STARTING_QUANTITY, priceInCents);
        }else  if (candyDataArray[0].equalsIgnoreCase("SR")) {
            return new Sours(id, name, isIndividuallyWrapped, STARTING_QUANTITY, priceInCents);
        }else  if (candyDataArray[0].equalsIgnoreCase("HC")) {
            return new HardCandy(id, name, isIndividuallyWrapped, STARTING_QUANTITY, priceInCents);
        }else  if (candyDataArray[0].equalsIgnoreCase("LI")) {
            return new Licorice(id, name, isIndividuallyWrapped, STARTING_QUANTITY, priceInCents);
        }else {
            return null;
        }

    }

    public void fileReader () {
        try(Scanner reader = new Scanner(this.inventoryFile)) {

            while (reader.hasNextLine()) {
                CandyStoreItem candyItemToAdd = textToCandyObject(reader.nextLine());
                if (candyItemToAdd != null) {
                    listOfCandyItems.add(candyItemToAdd);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found " );
        }
    }

}
