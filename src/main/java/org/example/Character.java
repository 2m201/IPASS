package org.example;

import java.util.ArrayList;

public class Character {
    private String name;
    private String gender;
    private String personality;
    private String species;
    private String birthday;

    public Character(String name, String gender, String personality, String species, String birthday) {
        if (Data.allCharacters.stream().noneMatch(kList -> kList.getName().equals(name))) {
            this.name = name;
            this.gender = gender;
            this.personality = personality;
            this.species = species;
            this.birthday = birthday;
            Data.allCharacters.add(this);
            System.out.println("Creating " + name + " has succeeded!");
        } else {
            System.out.println("The character you are trying to create already exixsts.");
        }
    }

    public String getName() { return name; }
    public String getGender(){ return gender; }
    public String getPersonality(){ return personality; }
    public String getSpecies(){ return species; }
    public String getBirthday(){ return birthday; }
    public static ArrayList<Character> getAllCharacters() { return Data.allCharacters; }

    public void setName(String name) { this.name = name;}
    public void setGender(String gender) { this.gender = gender;}
    public void setPersonality(String personality){ this.personality = personality; }
    public void setSpecies(String species){ this.species = species; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

}
