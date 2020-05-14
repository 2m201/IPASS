package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private static HashMap<String, Integer> map = new HashMap<>();

    static{
    map.put("Soft wood", 2);
    map.put("Hard wood", 6);
    }
    Item i1 = new Item("Bench", map);


    @Test
    void creatingTheSameItem(){
        Item i2 = new Item("Bench", map);

        assertEquals(1,Item.getAllItems().size());

    }

}