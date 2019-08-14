package com.twu.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private BookInventory book_inv;
    private ArrayList<String> menu_opt;

    protected BibliotecaApp(){
        setupBookInv();
        setupMenu();
    }

    private void setupMenu() {
        this.menu_opt = new ArrayList<String>();
        this.menu_opt.add("List of books");
        this.menu_opt.add("Checkout book");
        this.menu_opt.add("Quit");
    }

    private void setupBookInv() {
        this.book_inv = new BookInventory();
    }

    public static void main(String[] args) {
        BibliotecaApp biblioteca_app = new BibliotecaApp();
        biblioteca_app.onRun();
    }

    private void displayWelcomeMsg(){
        System.out.println("Welcome to Biblioteca. " +
                "You're one-stop-shop for great book titles in Bangalore!");
    }

    protected void onRun(){
        displayWelcomeMsg();
        openMenu();
    }

    private void openMenu() {
        displayMenu();

        Scanner user_input = new Scanner(System.in);
        Integer selection = user_input.nextInt();

        while(selection != this.menu_opt.size()){
            if(selection == 1){
                displayBookInv();
            }
            else if(selection == 2){
                checkOutBook();
                System.out.println("Thank you! Enjoy the book");
            }
            else{//invalid input
                System.out.println("Please select a valid option!");
            }
            displayMenu();
            selection = user_input.nextInt();
        }
        onQuit();
    }

    private void checkOutBook() {
        System.out.println("\nPlease select the number next to the book you want to checkout");
        displayBookInv();
        System.out.printf("%d)\tBack\n", this.book_inv.getNumCheckedIn() + 1);

        Scanner user_input = new Scanner(System.in);
        int selection = user_input.nextInt();

        if(selection == this.book_inv.getNumCheckedIn() + 1){
            openMenu();
        }
        else if(selection < 1 || selection > this.book_inv.getNumCheckedIn()){
            System.out.println("Please enter a valid option");
        }
        else{
            checkOutBook(selection);
        }
    }

    protected void checkOutBook(Integer selection) {
        this.book_inv.checkOutBook(selection);
    }


    private void displayMenu(){
        System.out.print("\nPlease select from the following options\n");
        int i = 0;
        for(String menu_opt: this.menu_opt){
            i++;
            System.out.printf("%d)\t%s\n", i, menu_opt);
        }
    }

    protected void displayBookInv(){
        this.book_inv.displayBookInventory();
    }

    private void onQuit(){
        System.out.println("Thanks for using Biblioteca!");
        System.exit(0);
    }


}
