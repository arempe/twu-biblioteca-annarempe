package com.twu.biblioteca;
import java.io.PrintStream;

public class MovieInventory extends Inventory{
    public MovieInventory(PrintStream out){
        super(out, String.format("\t%-30s\t%-5s\t%-30s\t%-3s\t\n", "Title", "Year", "Director", "Rating"));

        Movie movie = new Movie("Warrior", 2011, "Gavin O'Connor", 9);
        movie.setStatus(false);
        movie.setCheckedOutBy("111-1111");

        this.inventory.add(new Movie("Moonlight", 2016, "Barry Jenkins", 7));
        this.inventory.add(movie);
        this.num_checked_in = 1;
    }
}
