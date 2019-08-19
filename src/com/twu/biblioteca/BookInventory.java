package com.twu.biblioteca;

import java.io.PrintStream;

public class BookInventory extends Inventory {
    public BookInventory(PrintStream out){
        super(out, String.format("\t%-20s\t%-20s\t%-5s\n", "Title", "Author", "Year"));

        Book to_add = new Book("Green Eggs and Ham", "Dr. Seuss", 1960);
        to_add.setStatus(false);
        this.inventory.add(to_add);

        this.inventory.add(new Book("The Name of the Wind", "Patrick Rothfuss", 2007));
        this.inventory.add(new Book("Milk and Honey", "Rupi Kaur", 2015));

        this.num_checked_in = 2;
    }
}
