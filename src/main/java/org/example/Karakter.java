package org.example;

import java.util.ArrayList;

public class Karakter {
    private String naam;
    private String geslacht;
    private String persoonlijkheid;
    private String soort;
    private String geboortedatum;

    public Karakter(String naam, String geslacht, String persoonlijkheid, String soort, String geboortedatum) {
        if (Data.alleKarakters.stream().noneMatch(kList -> kList.getNaam().equals(naam))) {
            this.naam = naam;
            this.geslacht = geslacht;
            this.persoonlijkheid = persoonlijkheid;
            this.soort = soort;
            this.geboortedatum = geboortedatum;
            Data.alleKarakters.add(this);
            System.out.println("Het aanmaken van karakter " + naam + " is gelukt.");
        } else {
            System.out.println("Het karakter dat u probeert te creëren bestaat al.");
        }
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public static ArrayList<Karakter> geefAlleKarakters() {
        return Data.alleKarakters;
    }
}
