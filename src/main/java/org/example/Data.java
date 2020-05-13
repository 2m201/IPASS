package org.example;

import java.util.ArrayList;

public class Data {
    protected static ArrayList<Karakter> alleKarakters = new ArrayList<>();
    protected static ArrayList<Account> alleAccounts = new ArrayList<>();

    public static void resetData(){
        alleKarakters.clear();
        alleAccounts.clear();
    }

    }


