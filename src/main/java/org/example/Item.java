package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    private String name;
    private HashMap<Material, Integer> materialList = new HashMap<>() ;

    public Item(String name, HashMap<Material, Integer> list){
        if (Data.allItems.stream().noneMatch(iList -> iList.getName().equals(name))){ // if the parameter name does not exist in the list -> nothing matches, so true comes out! (noneMatch -> true)
            this.name = name;
            for (Material item : list.keySet()){ //iterates through the material list with the keys (the name of the material)
                materialList.put(item, list.get(item)); //first puts the name of the material in the list and then gets the amount of the material (list.get(item))!
            }
            Data.allItems.add(this);
        }else{ // if noneMatch equals false (so something DOES match)
            System.out.println("The item you are trying to create already exists!");
            }
    }

    public String getName(){ return name; }
    public HashMap<Material, Integer> getMaterialList() { return materialList; }
    public static ArrayList<Item> getAllItems(){ return Data.allItems; }
}
