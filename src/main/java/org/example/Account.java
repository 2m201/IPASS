package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Account {
    private String emailadres;
    private String wachtwoord;
    private String type = "Gebruiker";
    private String naam;

    private ArrayList<Karakter> favoriet_karakter = new ArrayList<>();
    private ArrayList<Karakter> huidig_karakter = new ArrayList<>();
    private ArrayList<Materiaal> opgeslagen_materialen = new ArrayList<>();

    public Account(String emailadres , String wachtwoord, String naam){
        if (Data.alleAccounts.stream().noneMatch(aList -> aList.getEmailadres().equals(emailadres))) {
            if (wachtwoord.length() < 8){
                System.out.println("Uw wachtwoord is te klein. Voer een wachtwoord in dat groter is dan 8 karakters!");
            }else{
                this.emailadres = emailadres;
                this.wachtwoord = wachtwoord;
                this.naam = naam;
                Data.alleAccounts.add(this);
            }
        }else{
            System.out.println("Het emailadres dat u heeft ingevoerd staat al in ons systeem. Voer een ander emailadres in!");
        }
    }


    public String getEmailadres(){ return emailadres; }


}
