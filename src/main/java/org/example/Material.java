package org.example;

import java.util.ArrayList;

public class Material {
    private String name;


    public Material(String name) { // constructor om nieuw materiaal te creëren
        if (Data.allMaterials.stream().noneMatch(mList -> mList.getName().equals(name))) {
        this.name = name;
        Data.materialList.put(name, 0);
        Data.allMaterials.add(this);
        }
        else{
            System.out.println("The material you tried to create already exists!");
        }

    }

    public String getName(){ return name; }

    public static ArrayList<Material> getAllMaterials(){
        return Data.allMaterials;
    }

}