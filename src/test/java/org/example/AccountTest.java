package org.example;

import org.example.domein.Account;
import org.example.domein.Character;
import org.example.domein.Data;
import org.example.domein.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account a1;

    @BeforeEach
    public void init(){
        Data.getData().resetData();
        Account a1 = new Account("melaniekelley@hotmail.com", "melaniekelleey", "Melanie Kelley");
    }

    @Test
    void creatingTheSameAccount(){
        Account a2 = new Account("melaniekelley@hotmail.com", "melaniekelleey", "Melanie Kelley");
        //assertEquals(1, Data.getData().allAccounts.size() );
    }

    @Test
    void passwordTooShort(){
        Account a3 = new Account("a.b.a.vandehoef@hotmail.com", "annemae", "Annemae van de Hoef");
        //assertEquals(1, Data.allAccounts.size() );
    }

    @Test
    void changingRoleToAdmin(){
        Account a4 = new Account("Kiki@deliveryservice.com", "rakakakakakaka", "Annemae van de Hoef");
        Account.setAdministrator(a4);
        assertEquals("Admin", a4.getRole());
    }

    @Test
    void addMaterialsToList(){
        Account a5 = new Account("meelaaanniiieee", "eeeeeeeeee", "Melanie"); //ask mae why it works for a5 but not for a1??
        Material m1 = new Material("Softwood");
        Material m2 = new Material ("Stone");

        HashMap<Material, Integer> list = new HashMap<>();
        list.put(m1, 3);
        list.put(m2,7);
        a5.addSavedMaterials(list);
        assertEquals(2, a5.getSavedMaterials().size());
    }

    @Test
    void addCharacterTwice(){
        Character c2 = new Character("Sherb","Male","Lazy","Goat","January 18");
    }



}