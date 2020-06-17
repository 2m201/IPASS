package org.example.domein;

import java.awt.*;
import java.io.Serializable;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class Character implements Serializable {
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

    public static List<Character> searchCharacter(String input) {
        List<Character> characterList = new ArrayList<>();
        for (Character item : Data.getData().allCharacters) {
            if (item.getName().equals(input) || item.getPersonality().equals(input) || item.getSpecies().equals(input)) {
                System.out.println("The character " + item.getName() + " exists");
                characterList.add(item);
            }
        }
        return characterList;
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getPersonality() { return personality; }
    public String getSpecies() { return species; }
    public String getBirthday() { return birthday; }
    public String getPicture() { return picture; }
    public String getCatchphrase() { return catchphrase; }
    public String getDescription() { return description; }

    public static ArrayList<Character> getAllCharacters() { return Data.getData().allCharacters; }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPersonality(String personality) { this.personality = personality; }
    public void setSpecies(String species) { this.species = species; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setCatchphrase(String catchphrase) { this.catchphrase = catchphrase; }
    public void setDescription(String description) { this.description = description; }

    public static boolean deleteCharacter(String name) {
        for (Character item : Data.getData().getAllCharacters()) {
            if (item.getName().equals(name)) {
                Data.getData().allCharacters.remove(item);
                return true;
            }
        }
        return false;
    }

    public static void changeCharacter(String character, String name, String URL, String gender, String personality,
                                       String species, String birthday, String catchphrase, String description) throws Exception {
        System.out.println( " yee" + name);
        Character character1 = Data.getData().getCharacterByName(character);

        System.out.println("yeet");

        if (!(URL.isEmpty())) {
            character1.setPicture(URL);
        }
        if (!(gender.isEmpty())) {
            character1.setGender(gender);
        }
        if (!(personality.isEmpty())) {
            character1.setPersonality(personality);
        }
        if (!(species.isEmpty())) {
            character1.setSpecies(species);
        }
        if (!(birthday.isEmpty())) {
            character1.setBirthday(birthday);
        }
        if (!(catchphrase.isEmpty())) {
            character1.setCatchphrase(catchphrase);
        }
        if (!(description.isEmpty())) {
            character1.setDescription(description);
        }

        } //TODO NOT DONE YET  ERROR HANDLING TO BE DONE


}
