package com.twu.biblioteca;


import java.util.ArrayList;

class Account{
    private String lib_num;
    private String pass;
    private String name;
    private String number;
    private String email;

    public Account(String lib_num, String pass){
        this.lib_num = lib_num;
        this.pass = pass;
    }

    public Account(String lib_num, String pass, String name, String number, String email){
        this.lib_num = lib_num;
        this.pass = pass;
        this.name = name;
        this.number = number;
        this.email = email;
    }
    public boolean isEqual(Account lhs){
        boolean is_equal = false;
        if((this.lib_num.equals(lhs.lib_num)) && (this.pass.equals(lhs.pass))){
            is_equal = true;
        }
        return is_equal;
    }
    public String toString(){
        String account_info = String.format("Name: %s\n"
                + "Number: %s\n"
                + "Email: %s\n",
                this.name, this.number, this.email
        );
        return account_info;
    }

    public String getLibNum() {
        return this.lib_num;
    }
}

public class Accounts {
    private ArrayList<Account> accounts;

    public Accounts(){
        accounts = new ArrayList<Account>();
        accounts.add(new Account("111-1111", "pass", "Jon Doe", "555-555-5555", "xxx@xxx.com"));
        accounts.add(new Account("222-2222", "1234", "Jane Doe", "555-555-5555", "xxx@xxx.com"));
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

    public Account searchUsrs(String lib_num) {
        Account to_return = null;
        for(Account account : this.accounts){
            if(account.getLibNum().equals(lib_num)){
                to_return = account;
                break;
            }
        }
        return to_return;
    }
}
