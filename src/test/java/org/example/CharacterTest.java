package org.example;

import org.example.domein.Character;
import org.example.domein.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character k1;
    private Character k2;
    private Character k3;

    @BeforeEach
    public void init(){
        k1 = new Character("Kiki", "Female", "Normal", "Cat", "October 8", "Yee" , "yes");
        System.out.println("TEST");
    }

    @Test
    void creatingTheSameCharacter(){
        k2 = new Character("Kiki", "Female", "Normal", "Cat", "October 8", "Yee" , "yes");
        assertEquals(1, Character.getAllCharacters().size());
    }



}