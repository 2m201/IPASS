package org.example.domein;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

public class Account implements Principal, Serializable {
    private String username;
    private String password;
    private String role;

    private ArrayList<Character> favouriteCharacter = new ArrayList<>();
    private ArrayList<Character> currentCharacter = new ArrayList<>();
    private HashMap<Material, Integer> savedMaterials = new HashMap<>();

    public Account(String username, String password){
        if (Data.getData().allAccounts.stream().noneMatch(aList -> aList.getName().equals(username))) {
            if (password.length() < 8){
                System.out.println("The password that you filled in is too short. Please fill in a password that is longer than eight characters!");
            }else{
                this.username = username;
                this.password = password;
                this.role = "user";
                this.savedMaterials = (HashMap<Material, Integer>) Material.getDefaultList().clone();
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
        }
    }

    @Override
    public String getName(){ return username; }
    public String getRole(){ return role; }
    public String getPassword() { return password; }

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
    public void addCharacter(String list, Character character) throws Exception {
        if (list.equals("current")) {
            if (currentCharacter.size() >= 9) {
                throw new Exception("List has reached max capacity");
            } else {
                if (currentCharacter.contains(character)) {
                    throw new Exception("The character you are trying to add already has been added!");
                } else {
                    currentCharacter.add(character);
                }
            }
        } else if (list.equals("favourite")) {
            if (favouriteCharacter.contains(character)) {
                throw new Exception("The character you are trying to add already has been added!");
            } else {
                favouriteCharacter.add(character);
            }
        }
    }
    public static String validateLogin(String username, String password){
        Account found = Data.getData().getAccountByName(username);
        if (found != null){
            return password.equals(found.password) ? found.getRole() : null;
        }
        return null;
    }

//    public void addFavouriteCharacter(Character character){
//        if (favouriteCharacter.contains(character)){
//            System.out.println("The character you are trying to add already has been added!");
//        } else {
//            favouriteCharacter.add(character);
//        }
//    }
//    public void addCurrentCharacter(Character character){
//        if (currentCharacter.contains(character)){
//            System.out.println("The character you are trying to add already has been added!");
//        }else {
//            currentCharacter.add(character);
//        }
//    }

}





