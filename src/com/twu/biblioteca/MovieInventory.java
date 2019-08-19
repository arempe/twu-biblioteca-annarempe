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
        Movie movie = new Movie("Warrior", 2011, "Gavin O'Connor", 9);
        movie.setStatus(false);
        movie.setCheckedOutBy("111-1111");
        this.movie_inv.add(movie);
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

    public boolean checkOutMovie(int selection, String usr_lib_num) {
        //selection based off of list of books which only displays available movies
        int ind = selectionToInd(selection);
        boolean success = false;
        Movie to_check_out = this.movie_inv.get(ind);
        if(to_check_out.getStatus()) {
            to_check_out.setStatus(false);
            to_check_out.setCheckedOutBy(usr_lib_num);
            this.num_checked_in--;
            success = true;
        }
        else{
            this.out.println("Movie is not available");
        }
        return success;
    }

    public Movie searchInv(String title) {
        Movie to_return = null;
        for(Movie movie : this.movie_inv){
            if(movie.getTitle().equals(title)){
                to_return = movie;
            }
        }
        return to_return;
    }

    public boolean checkInMovie(String movie_title) {
        boolean check_in_success = false;
        for(Movie movie : this.movie_inv){
            if(movie.getTitle().equals(movie_title)){
                if(!movie.getStatus()){
                    movie.setStatus(true);
                    movie.setCheckedOutBy(null);
                    this.num_checked_in++;
                    check_in_success = true;
                    break;
                }
            }
        }
        return check_in_success;
    }
}
