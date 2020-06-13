package org.example.domein;

import java.io.Serializable;
import java.util.ArrayList;

public class Material implements Serializable {
    private String name;


    public Material(String name) { // constructor om nieuw materiaal te creëren
        if (Data.getData().allMaterials.stream().noneMatch(mList -> mList.getName().equals(name))) {
        this.name = name;
        Data.getData().allMaterials.add(this);
        }
        else{
            System.out.println("The material you tried to create already exists!");
        }

    }

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

}