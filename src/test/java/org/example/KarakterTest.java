package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KarakterTest {
    private Karakter k1;
    private Karakter k2;
    private Karakter k3;

    @BeforeEach
    public void init(){
        Data.resetData();
        k1 = new Karakter("Kiki", "Female", "Normal", "Cat", "October 8");
        System.out.println("TEST");
    }

    @Test
    void hetzelfdeKarakterMaken(){
        k2 = new Karakter("Kiki", "Female", "Normal", "Cat", "October 8");
        assertEquals(1, Karakter.geefAlleKarakters().size());
    }

}