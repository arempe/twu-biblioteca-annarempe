package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class BibliotecaApp {

    private BookInventory book_inv;

    private ArrayList<String> menu_opt;
    private int quit_opt;
    private PrintStream out;
    private InputWrapper in_wrap;

    protected BibliotecaApp(PrintStream out, InputWrapper in_wrap){
        this.out = out;
        this.in_wrap = in_wrap;
        setupMenu();
        setupBookInv();

    }

    private void setupMenu() {
        this.menu_opt = new ArrayList<String>();
        this.menu_opt.add("List of books");
        this.menu_opt.add("Checkout book");
        this.menu_opt.add("Check-in book");
        this.menu_opt.add("Quit");
        this.quit_opt = this.menu_opt.size();
    }


    public String menuToString(){
        String to_return;
        to_return = "\nPlease select from the following options\n";
        int i = 0;
        for(String menu_opt: this.menu_opt){
            i++;
            to_return += String.format("%d)\t%s\n", i, menu_opt);
        }
        return to_return;
    }

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        InputWrapper input_wrapper = new InputWrapper();
        BibliotecaApp biblioteca_app = new BibliotecaApp(out, input_wrapper);
        biblioteca_app.onRun();
        biblioteca_app.openMenu();
    }



    private void setupBookInv() {
        this.book_inv = new BookInventory(this.out);
    }

    private void displayWelcomeMsg(){
        this.out.println("Welcome to Biblioteca. " +
                "You're one-stop-shop for great book titles in Bangalore!");
    }

    protected void onRun(){
        displayWelcomeMsg();
       // openMenu();
    }

    protected void openMenu() {
        int selection = -1;
        while(selection != this.quit_opt){
            displayMenu();
            selection = this.in_wrap.getInt();

            if(selection == 1){
                displayBookInv();
            }
            else if(selection == 2){
                checkOutBook();

            }
            else if(selection == 3){
                checkInBook();
            }
            else if(selection == this.quit_opt){
                onQuit();
                break;
            }
            else{//invalid input
                this.out.println("Please select a valid option!");
            }
        }
    }

    private void checkInBook() {
        this.out.println("");
    }

    public void checkInBook(String book_title) {
    }

    private void checkOutBook() {
        this.out.println("\nPlease select the number next to the book you want to checkout");
        displayBookInv();
        this.out.printf("%d)\tBack\n", this.book_inv.getNumCheckedIn() + 1);

        int selection = this.in_wrap.getInt();

        if(selection == this.book_inv.getNumCheckedIn() + 1){
            openMenu();
        }
        else if(selection < 1 || selection > this.book_inv.getNumCheckedIn()){
            this.out.println("Sorry, that book is not available");
        }
        else{
            checkOutBook(selection);
            this.out.println("Thank you! Enjoy the book");
        }
    }

    protected void checkOutBook(Integer selection) {
        this.book_inv.checkOutBook(selection);
    }


    private void displayMenu(){
        this.out.print(this.menuToString());
    }

    public void displayBookInv(){
        this.book_inv.displayBookInventory();
    }

    private void onQuit(){
        this.out.println("Thanks for using Biblioteca!");
    }


}
