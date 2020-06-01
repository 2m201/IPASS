package org.example.domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Character implements Serializable{
    private String name;
    private String gender;
    private String personality;
    private String species;
    private String birthday;
    private String picture;
    private String catchphrase;
    private String description;

    public Character(String name, String gender, String personality, String species, String birthday, String catchphrase, String description) {

        if (Data.getData().allCharacters.stream().noneMatch(kList -> kList.getName().equals(name))) {
            this.name = name;
            this.gender = gender;
            this.personality = personality;
            this.species = species;
            this.birthday = birthday;
            this.catchphrase = catchphrase;
            this.description = description;
            Data.getData().allCharacters.add(this);
            System.out.println("Creating " + name + " has succeeded!");
        } else {
            System.out.println("The character you are trying to create already exists.");
        }
    }

    public static List<Character> searchCharacter(String input){
        List<Character> characterList = new ArrayList<>();
        for (Character item : Data.getData().allCharacters){
            if (item.getName().equals(input) || item.getPersonality().equals(input) || item.getSpecies().equals(input)){
                System.out.println("The character " + item.getName() + " exists");
                characterList.add(item);
            }
        }
        return characterList;
    }

    public String getName() { return name; }
    public String getGender(){ return gender; }
    public String getPersonality(){ return personality; }
    public String getSpecies(){ return species; }
    public String getBirthday(){ return birthday; }
    public String getPicture() { return picture; }

    public String getCatchphrase() { return catchphrase; }
    public String getDescription() { return description; }

    public static ArrayList<Character> getAllCharacters() { return Data.getData().allCharacters; }

    public void setName(String name) { this.name = name;}
    public void setGender(String gender) { this.gender = gender;}
    public void setPersonality(String personality){ this.personality = personality; }
    public void setSpecies(String species){ this.species = species; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setPicture(String picture) {this.picture = picture;}
    public void setCatchphrase(String catchphrase) { this.catchphrase = catchphrase; }
    public void setDescription(String description) { this.description = description; }

}
