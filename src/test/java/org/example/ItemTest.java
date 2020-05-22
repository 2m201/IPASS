package org.example;

import org.example.domein.Data;
import org.example.domein.Item;
import org.example.domein.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private HashMap<Material, Integer> map = new HashMap<>();
    Material m1 = new Material("Softwood");
    Material m2 = new Material ("Stone");

    @BeforeEach
    void init(){
        //Data.resetData();
        map.put(m1,3);
        map.put(m2,4);
        Item i1 = new Item("Bench", map);

    }


    @Test
    void creatingTheSameItem(){
        Item i2 = new Item("Bench", map);

        assertEquals(1,Item.getAllItems().size());

    }

}