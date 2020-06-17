package org.example.domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data implements Serializable {
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

    public List<Character> getAllCharacters(){
        return Collections.unmodifiableList(allCharacters);
    }

    public List<Account> getAllAccounts(){
        return  Collections.unmodifiableList(allAccounts);
    }

    public Account getAccountByName(String name){
        return getAllAccounts().stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Character getCharacterByName(String name){
        return getAllCharacters().stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void clearCharacterList(){
        allCharacters.clear();
    }


}


