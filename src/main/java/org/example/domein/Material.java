package org.example.domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Material implements Serializable {
    private String name;
    private int amount;
    private static HashMap<Material, Integer> defaultList = new HashMap<>();


    public Material(String name) {
        if (Data.getData().allMaterials.stream().noneMatch(mList -> mList.getName().equals(name))) {
        this.name = name;
        Data.getData().allMaterials.add(this);
        this.amount = 0;
        defaultList.put(this, amount);
        }
        else{
            System.out.println("The material you tried to create already exists!");
        }

    } // constructor om nieuw materiaal te creëren

    public String getName(){ return name; }

    public static ArrayList<Material> getAllMaterials(){
        return Data.getData().allMaterials;
    }

    public static Material getMaterialByName(String nam){
        return Data.getData().allMaterials.stream()
                .filter(item -> item.name.equals(nam))
                .findFirst()
                .orElse(null);
    }

    public static HashMap<Material, Integer> getDefaultList() {
        return defaultList;
    }
}