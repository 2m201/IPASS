package org.example;

import java.util.HashMap;

public class TEst {

    public static void main(String[] args) {
        Material m1 = new Material("Softwood");
        Material m2 = new Material ("Stone");

        HashMap<Material, Integer> list1 = new HashMap<>();
        list1.put(m1, 2);
        list1.put(m2,4);

        Item i1 = new Item("Bench", list1);
        System.out.println("The material list for the item " + i1.getName() + " is: ");
        for (Material item : i1.getMaterialList().keySet()) {
            System.out.println(item.getName() + " : " + i1.getMaterialList().get(item));
        }


    }

}
