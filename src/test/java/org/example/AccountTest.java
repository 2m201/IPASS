package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account a1;


    @BeforeEach
    public void init(){
        Data.resetData();
        Account a1 = new Account("melaniekelley@hotmail.com", "melaniekelleey", "Melanie Kelley");
    }

    @Test
    void creatingTheSameAccount(){
        Account a2 = new Account("melaniekelley@hotmail.com", "melaniekelleey", "Melanie Kelley");
        assertEquals(1, Data.allAccounts.size() );
    }

    @Test
    void passwordTooShort(){
        Account a3 = new Account("a.b.a.vandehoef@hotmail.com", "annemae", "Annemae van de Hoef");
        assertEquals(1, Data.allAccounts.size() );
    }

    @Test
    void changingRoleToAdmin(){
        Account a4 = new Account("Kiki@deliveryservice.com", "rakakakakakaka", "Annemae van de Hoef");
        Account.setAdministrator(a4);
        assertEquals("Admin", a4.getType());
    }



}