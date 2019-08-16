package com.twu.biblioteca;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private BookInventory book_inv;

    private ArrayList<String> menu_opt;
    private int quit_opt;
    private PrintStream out;
    private InputWrapper in_wrap;

    // msgs
    private String invalid_option_msg = "Please select a valid option!\n";
    private String quit_msg = "Thank you for using BibliotecaApp!\n";
    private String check_out_header_msg = "Please select the number next to the book you want to checkout\n";
    private String check_in_header_msg = "Please enter the title of the book you are checking in\n";

    protected BibliotecaApp(PrintStream out, InputWrapper in_wrap){
        this.out = out;
        this.in_wrap = in_wrap;
        setupMenu();
        setupBookInv();

    }

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        InputWrapper input_wrapper = new InputWrapper();
        BibliotecaApp biblioteca_app = new BibliotecaApp(out, input_wrapper);
        biblioteca_app.onRun();
        biblioteca_app.openMenu();
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
        StringBuilder to_return;
        to_return = new StringBuilder("\nPlease select from the following options\n");
        int i = 0;
        for(String menu_opt: this.menu_opt){
            i++;
            to_return.append(String.format("%d)\t%s\n", i, menu_opt));
        }
        return to_return.toString();
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
    }

    protected  void openMenu(){
        displayMenu();
        int selection = this.in_wrap.getInt();
        openMenu(selection);
    }
    protected void openMenu(int selection) {
        while(selection != this.quit_opt){
            if(selection == 1){
                displayBookInv();
            }
            else if(selection == 2){
                checkOutBook();

            }
            else if(selection == 3){
                checkInBook();
            }
            else{//invalid input
                this.out.print(this.invalid_option_msg);
            }
            displayMenu();
            selection = this.in_wrap.getInt();
        }
        onQuit();
    }

    private void checkInBook() {
        this.out.print(this.check_in_header_msg);
        String book_title = this.in_wrap.getString();
        checkInBook(book_title);
    }

    public void checkInBook(String book_title) {
        this.book_inv.checkInBook(book_title);

    }

    private void checkOutBook() {
        this.out.print(this.check_out_header_msg);
        displayBookInv();
        this.out.printf("%d)\tBack\n", this.book_inv.getNumCheckedIn() + 1);

        int selection = this.in_wrap.getInt();

        if(selection == this.book_inv.getNumCheckedIn() + 1){
            //return to menu
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
        this.out.println(this.menuToString());
    }

    public void displayBookInv(){
        this.book_inv.displayBookInventory();
    }

    private void onQuit(){
        this.out.print(this.quit_msg);
    }

    protected String getQuitMsg(){
        return this.quit_msg;
    }

    public String getInvalidMsg() {
        return this.invalid_option_msg;
    }
    public String getCheckOutMsg(){
        return this.check_out_header_msg;
    }

    public String getInvHeader() {
        return this.book_inv.getInvHeaderMsg();
    }

    public String getCheckInMsg() {
        return this.check_in_header_msg;
    }
}
