package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    protected static ArrayList<Character> allCharacters = new ArrayList<>();
    protected static ArrayList<Account> allAccounts = new ArrayList<>();
    protected static ArrayList<Material> allMaterials = new ArrayList<>();
    protected static ArrayList<Item> allItems = new ArrayList<>();

    protected static HashMap<String, Integer> materialList = new HashMap<>();
    static {
        materialList.put("Softwood",0);
        materialList.put("Clump of weed",0);
        materialList.put("Tree Branch",0);
        materialList.put("Wood",0);
        materialList.put("Hardwood",0);
        materialList.put("Stone",0);

    }


    public static void resetData(){
        allCharacters.clear();
        allAccounts.clear();
        }

}


