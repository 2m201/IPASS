package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    private String name;
    private HashMap<String, Integer> materialList = Data.materialList;

    public Item(String name, HashMap<String, Integer> list){
        if (Data.allItems.stream().noneMatch(iList -> iList.getName().equals(name))){
            this.name = name;
            for (String item : list.keySet()){
//                if(list.get(item) == 0)   check if amount is zero
                materialList.put(item, materialList.get(item) + list.get(item));
            }
            Data.allItems.add(this);
        }else{
            System.out.println("The item you are trying to create already exists!");
            }
    }

    public String getName(){ return name; }
    public HashMap<String, Integer> getMaterialList() { return materialList; }
    public static ArrayList<Item> getAllItems(){ return Data.allItems; }
}
