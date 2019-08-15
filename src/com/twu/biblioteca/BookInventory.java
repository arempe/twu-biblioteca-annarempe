package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class BookInventory {
    private int num_checked_in;
    private ArrayList<Book> book_inv;
    private PrintStream out;


    public BookInventory(PrintStream out){
        this.out = out;
        this.book_inv = new ArrayList<Book>();
        this.book_inv.add(new Book("The Name of the Wind", "Patrick Rothfuss", 2007, this.out));
        Book to_add = new Book("Green Eggs and Ham", "Dr. Seuss", 1960, this.out);
        to_add.setStatus(false);
        this.book_inv.add(to_add);
        this.book_inv.add(new Book("Milk and Honey", "Rupi Kaur", 2015, this.out));
        this.num_checked_in = 2;
    }


    public void checkOutBook(int selection){
        //selection based off of list of books which only displays available books
        int ind = selectionToInd(selection);
        Book to_check_out = this.book_inv.get(ind);
        if(to_check_out.getStatus()) {
            to_check_out.setStatus(false);
            this.num_checked_in--;
        }
        else{
            this.out.println("Book is not available");
        }
    }

    protected void printCheckedInBooks(){
        int i = 0;
        for(Book book: this.book_inv){
            if(book.getStatus()){
                i++;
                this.out.printf("%d)\t", i);
                this.out.println(book.toString());
            }
        }
    }

    protected void displayBookInventory(){
        this.out.printf("\t%-20s\t%-20s\t%-5s\n", "Title", "Author", "Year");
        printCheckedInBooks();
    }


    private int selectionToInd(int selection) {
        int inv_itr = 0;
        int return_ind = -1;
        int usr_selection = selection;

        for(Book book: this.book_inv){
           if(book.getStatus()){
               usr_selection--;
           }
           if(usr_selection == 0){
               return_ind = inv_itr;
               break;
           }
           else{
               inv_itr++;
           }
        }
        return return_ind;
    }

    public int getNumCheckedIn() {
        return this.num_checked_in;
    }
}
