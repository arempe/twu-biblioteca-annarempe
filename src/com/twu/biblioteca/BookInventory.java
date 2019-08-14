package com.twu.biblioteca;

import java.util.ArrayList;

public class BookInventory {
    private int num_checked_in;
    private ArrayList<Book> book_inv;


    public BookInventory(){
        this.book_inv = new ArrayList<Book>();
        this.book_inv.add(new Book("The Name of the Wind", "Patrick Rothfuss", 2007));
        this.book_inv.add(new Book("Milk and Honey", "Rupi Kaur", 2015));
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
            System.out.println("Book is not available");
        }
    }

    protected void printCheckedInBooks(){
        int i = 0;
        for(Book book: this.book_inv){
            if(book.getStatus()){
                i++;
                System.out.printf("%d)\t", i);
                book.printBook();
            }
        }
    }

    protected void displayBookInventory(){
        System.out.printf("\t%-20s\t%-20s\t%-5s\n", "Title", "Author", "Year");
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
