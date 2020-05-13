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
    void hetzelfdeAccountMaken(){
        Account a2 = new Account("melaniekelley@hotmail.com", "melaniekelleey", "Melanie Kelley");
        assertEquals(1, Data.alleAccounts.size() );

    }

    @Test
    void wachtwoordTeKlein(){
        Account a3 = new Account("a.b.a.vandehoef@hotmail.com", "annemae", "Annemae van de Hoef");
        assertEquals(1, Data.alleAccounts.size() );


    }

}