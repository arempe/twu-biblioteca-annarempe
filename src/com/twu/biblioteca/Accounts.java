package com.twu.biblioteca;


import java.util.ArrayList;

class Account{
    private String lib_num;
    private String pass;

    public Account(String lib_num, String pass){
        this.lib_num = lib_num;
        this.pass = pass;
    }
    public boolean isEqual(Account lhs){
        boolean is_equal = false;
        if((this.lib_num.equals(lhs.lib_num)) && (this.pass.equals(lhs.pass))){
            is_equal = true;
        }
        return is_equal;
    }
    public String toString(){
        return this.lib_num + " " + this.pass;
    }
}

public class Accounts {
    private ArrayList<Account> accounts;

    public Accounts(){
        accounts = new ArrayList<Account>();
        accounts.add(new Account("111-1111", "pass"));
        accounts.add(new Account("222-2222", "1234"));
    }

    private boolean containsAccount(String lib_num, String pass){
        boolean contains_account = false;
        Account new_account = new Account(lib_num, pass);
        for(Account account : this.accounts) {
            if(account.isEqual(new_account)){
                contains_account = true;
                break;
            }
        }
        return contains_account;
    }

    public boolean login(String lib_num, String pass){
        boolean login_success = false;
        if(containsAccount(lib_num, pass)){
            login_success = true;
        }
        return login_success;
    }
}
