package com.twu.biblioteca;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BibliotecaApp {

    private ArrayList<Book> book_inv;

    protected BibliotecaApp(){
        book_inv = new ArrayList<Book>();
        book_inv.add(new Book("The Name of the Wind"));
        book_inv.add(new Book("milk and honey"));
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
        displayBookInventory();
    }

    private void displayBookInventory(){
        int i = 0;
        System.out.println("\tTitle");
        for(Book book: this.book_inv){
            i++;
            System.out.printf("%d)\t", i);
            book.printBook();
        }
    }
}
