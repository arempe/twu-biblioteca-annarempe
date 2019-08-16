package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class MovieInventory {
    private String inv_header_msg = String.format("\t%-30s\t%-5s\t%-30s\t%-3s\t\n", "Title", "Year", "Director", "Rating");

    private PrintStream out;

    private ArrayList<Movie> movie_inv;

    public MovieInventory(PrintStream out){
        this.out = out;
        this.movie_inv = new ArrayList<Movie>();
        this.movie_inv.add(new Movie("Moonlight", 2016, "Barry Jenkins", 7));
    }

    public String getInvHeader() {
        return this.inv_header_msg;
    }

    public void displayMovieInventory() {
        this.out.print(this.inv_header_msg);
        int i = 0;
        for(Movie movie : this.movie_inv){
            i++;
            this.out.printf("%d)\t", i);
            this.out.print(movie.toString());
        }

    }
}
