package org.example.domein;

import java.util.ArrayList;

public class Data {
    protected ArrayList<Character> allCharacters = new ArrayList<>();
    protected ArrayList<Account> allAccounts = new ArrayList<>();
    protected ArrayList<Material> allMaterials = new ArrayList<>();
    protected ArrayList<Item> allItems = new ArrayList<>();

    private static Data dataObject = new Data();

    public static Data getData(){ return dataObject; }
    public static void setData(Data data){ dataObject = data; }

    public void resetData(){
        allCharacters.clear();
        allAccounts.clear();
        }

    public ArrayList<Character> getAllCharacters(){
        return allCharacters;
    }

}


