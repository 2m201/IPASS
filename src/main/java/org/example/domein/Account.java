package org.example.domein;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

public class Account implements Principal, Serializable {
    private String username;
    private String password;
    private String role;
    private String name;

    private ArrayList<Character> favouriteCharacter = new ArrayList<>();
    private ArrayList<Character> currentCharacter = new ArrayList<>();
    private HashMap<Material, Integer> savedMaterials = new HashMap<>();

    public Account(String username, String password, String name){
        if (Data.getData().allAccounts.stream().noneMatch(aList -> aList.getName().equals(username))) {
            if (password.length() < 8){
                System.out.println("The password that you filled in is too short. Please fill in a password that is longer than eight characters!");
            }else{
                this.username = username;
                this.password = password;
                this.name = name;
                this.role = "user";
                Data.getData().allAccounts.add(this);
            }
        }else{
            System.out.println("The username has already been registered in our system. Please use another!");
        }
    }

    public static void setAdministrator(Account account){
        if (!Data.getData().allAccounts.contains(account)){
            System.out.println("The account you want to change has not been registered in our system. Please use another!");
        }else {
            for (Account item : Data.getData().allAccounts) {
                if (item.getName().equals(account.getName())) {
                    account.role = "admin";
                    System.out.println("The role of account has been changed to the role Admin");
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

    @Override
    public String getName(){ return username; }

    public String getRole(){ return role; }


    public static String validateLogin(String username, String password){
        Account found = Data.getData().getAccountByName(username);
        if (found != null){
            return password.equals(found.password) ? found.getRole() : null;
        }
        return null;
    }

    public HashMap<Material, Integer> getSavedMaterials(){ return savedMaterials;}
    public ArrayList<Character> getCurrentCharacters() {
        return currentCharacter;
    }
    public ArrayList<Character> getFavouriteCharacters() {
        return favouriteCharacter;
    }

    public void setPassword(String oldPassword, String newPassword) throws Exception {
        if (!(oldPassword.isEmpty() || newPassword.isEmpty())) {
            if (!(oldPassword.equals(newPassword))) {
                if (oldPassword.equals(this.password)) {
                    if (newPassword.length() > 8) {
                        this.password = newPassword;
                    } else {
                        throw new Exception("The new password has to be at least 8 characters long");
                    }
                } else {
                    throw new Exception("The old password is not correct");
                }
            }
            else {throw new Exception("Please fill in different passwords");}
        } else {throw new Exception("Please fill in all the fields");}
    }

    public String getPassword() { return password; }}



