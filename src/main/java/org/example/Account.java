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
    HashMap<String, Integer> savedMaterials = Data.materialList;

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

    public String getEmail(){ return email; }
    public String getType(){ return type; }

    public static void setAdministrator(Account account){
        for (Account item : Data.allAccounts){
            if (item.getEmail() == account.getEmail()){
                account.type = "Admin";
                System.out.println("The type of account has been changed to Admin");
            }
        }
        if (!Data.allAccounts.contains(account)){
            System.out.println("The account you want to change has not been registered in our system. Please use another account!");
        }

    }



}
