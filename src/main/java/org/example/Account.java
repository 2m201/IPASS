package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    private String email;
    private String password;
    private String type = "User";
    private String name;

    private ArrayList<Character> favouriteCharacter = new ArrayList<>();
    private ArrayList<Character> currentCharacter = new ArrayList<>();
    private HashMap<Material, Integer> savedMaterials = new HashMap<>();

    public Account(String email , String password, String name){
        if (Data.allAccounts.stream().noneMatch(aList -> aList.getEmail().equals(email))) {
            if (password.length() < 8){
                System.out.println("The password that you filled in is too short. Please fill in a password that is longer than eight characters!");
            }else{
                this.email = email;
                this.password = password;
                this.name = name;
                Data.allAccounts.add(this);
            }
        }else{
            System.out.println("The emailadres that you filled in has already been registered in our system. Please use another emailadres!");
        }
    }

    public static void setAdministrator(Account account){
        if (!Data.allAccounts.contains(account)){
            System.out.println("The account you want to change has not been registered in our system. Please use another account!");
        }else {
            for (Account item : Data.allAccounts) {
                if (item.getEmail() == account.getEmail()) {
                    account.type = "Admin";
                    System.out.println("The type of account has been changed to Admin");
                }
            }
        }
    }

    public void addSavedMaterials(HashMap<Material, Integer> list){
        for (Material item : list.keySet()){ //iterates through the material list with the keys (the name of the material)
            savedMaterials.put(item, list.get(item)); //first puts the name of the material in the list and then gets the amount of the material (list.get(item))!
            System.out.println("The following materials have been added: " + list.keySet());
        }
    }

    public void addFavouriteCharacter(Character character){
        if (favouriteCharacter.contains(character)){
            System.out.println("The character you are trying to add already has been added!");
        } else {
            favouriteCharacter.add(character);
        }
    }

    public void addCurrentCharacter(Character character){
        if (currentCharacter.contains(character)){
            System.out.println("The character you are trying to add already has been added!");
        }else {
            currentCharacter.add(character);
        }
    }


    public String getEmail(){ return email; }
    public String getType(){ return type; }
    public HashMap<Material, Integer> getSavedMaterials(){ return savedMaterials;}



}
