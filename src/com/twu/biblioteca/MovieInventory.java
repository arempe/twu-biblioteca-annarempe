package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class MovieInventory {
    private String inv_header_msg = String.format("\t%-30s\t%-5s\t%-30s\t%-3s\t\n", "Title", "Year", "Director", "Rating");

    private PrintStream out;

    private ArrayList<Movie> movie_inv;
    private int num_checked_in;

    public MovieInventory(PrintStream out){
        this.out = out;
        this.movie_inv = new ArrayList<Movie>();
        this.movie_inv.add(new Movie("Moonlight", 2016, "Barry Jenkins", 7));
        this.num_checked_in = 1;
    }

    public String getInvHeader() {
        return this.inv_header_msg;
    }

    public void displayMovieInventory() {
        this.out.print(this.inv_header_msg);
        int i = 0;
        for(Movie movie : this.movie_inv){
            if(movie.getStatus()) {
                i++;
                this.out.printf("%d)\t", i);
                this.out.print(movie.toString());
            }
        }

    }

    public int getNumCheckedIn() {
        return this.num_checked_in;
    }

    private int selectionToInd(int selection) {
        int inv_itr = 0;
        int return_ind = -1;
        int usr_selection = selection;

        for(Movie movie: this.movie_inv){
            if(movie.getStatus()){
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

    public boolean checkOutMovie(int selection) {
        //selection based off of list of books which only displays available movies
        int ind = selectionToInd(selection);
        boolean success = false;
        Movie to_check_out = this.movie_inv.get(ind);
        if(to_check_out.getStatus()) {
            to_check_out.setStatus(false);
            this.num_checked_in--;
            success = true;
        }
        else{
            this.out.println("Movie is not available");
        }
        return success;
    }
}
