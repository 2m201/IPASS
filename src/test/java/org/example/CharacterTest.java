package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character k1;
    private Character k2;
    private Character k3;

    @BeforeEach
    public void init(){
        Data.resetData();
        k1 = new Character("Kiki", "Female", "Normal", "Cat", "October 8");
        System.out.println("TEST");
    }

    @Test
    void creatingTheSameCharacter(){
        k2 = new Character("Kiki", "Female", "Normal", "Cat", "October 8");
        assertEquals(1, Character.getAllCharacters().size());
    }



}