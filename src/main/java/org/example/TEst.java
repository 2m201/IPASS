package org.example;

import java.util.HashMap;

public class TEst {

    public static void main(String[] args) {
        System.out.println( "Oorspronkelijke lijst " + Data.materialList);
        HashMap<String, Integer> lijst = new HashMap<>();
        {
            lijst.put("Clump of weed", 6);
            lijst.put("Stone", 40);
        }

        System.out.println("Gemaakte lijst " + lijst);
        Item i1 = new Item("Axe", lijst);
        System.out.println("Lijst van i1 " + i1.getMaterialList());
        System.out.println("Template " + Data.materialList);
    }

}
