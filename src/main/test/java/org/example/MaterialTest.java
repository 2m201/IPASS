package org.example;

import org.example.domein.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

    @BeforeEach
    public void init(){
        Material m1 = new Material("Softwood");
    }

    @Test
    void creatingTheSameMaterial(){
        Material m2 = new Material("Softwood");
        assertEquals(1, Material.getAllMaterials().size());
    }

}