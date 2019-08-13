package com.twu.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private ArrayList<Book> book_inv;
    private ArrayList<String> menu_opt;

    protected BibliotecaApp(){
        setupBookInv();
        setupMenu();
    }

    private void setupMenu() {
        this.menu_opt = new ArrayList<String>();
        this.menu_opt.add("List of books");
        this.menu_opt.add("Quit");
    }

    private void setupBookInv() {
        this.book_inv = new ArrayList<Book>();
        this.book_inv.add(new Book("The Name of the Wind", "Patrick Rothfuss", 2007));
        this.book_inv.add(new Book("Milk and Honey", "Rupi Kaur", 2015));
    }

    public static void main(String[] args) {
        BibliotecaApp biblioteca_app = new BibliotecaApp();
        biblioteca_app.onRun();
    }

    private void displayWelcomeMsg(){
        System.out.println("Welcome to Biblioteca. " +
                "You're one-stop-shop for great book titles in Bangalore!");
    }

    private void onRun(){
        displayWelcomeMsg();
        openMenu();
    }

    private void openMenu() {
        displayMenu();

        Scanner user_input = new Scanner(System.in);
        Integer selection = user_input.nextInt();

        while(selection != this.menu_opt.size()){
            if(selection == 1){
                displayBookInventory();
            }
            else{//invalid input
                System.out.println("Please select a valid option!");
            }
            displayMenu();
            selection = user_input.nextInt();
        }
        System.out.println("Thanks for using Biblioteca!");
    }

    private void displayBookInventory(){
        System.out.printf("\t%-20s\t%-20s\t%-5s\n", "Title", "Author", "Year");
        int i = 0;
        for(Book book: this.book_inv){
            i++;
            System.out.printf("%d)\t", i);
            book.printBook();
        }
    }
    
    private void displayMenu(){
        System.out.print("\nPlease select from the following options\n");
        int i = 0;
        for(String menu_opt: this.menu_opt){
            i++;
            System.out.printf("%d)\t%s\n", i, menu_opt);
        }
    }
}
