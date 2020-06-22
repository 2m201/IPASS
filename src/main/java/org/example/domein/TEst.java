package org.example.domein;

import org.example.domein.Item;
import org.example.domein.Material;

import java.util.HashMap;

public class TEst {

    public static void main(String[] args) {
        Material softwood = new Material ("Soft wood");
        Material treebranch = new Material("Tree branch");
        Material clay = new Material("Clay");
        Material goldnugget = new Material("Gold nugget");
        Material ironnugget = new Material("Iron nugget");
        Material wood = new Material("Wood");
        Material hardwood = new Material("Hard wood");
        Material stone = new Material("Stone");
        Material clumpofweeds = new Material("Clump of weeds");
        Material starfragment = new Material("Star fragment");

        Account a1 = new Account("stinky", "stinky88888");


        Account a2 = new Account("mae", "isbignice");
        a1.getSavedMaterials().remove(stone);
        System.out.println(a1.getSavedMaterials());
        System.out.println(a2.getSavedMaterials());


    }

}
