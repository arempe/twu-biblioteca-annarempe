package com.twu.biblioteca;

public class Movie extends Item{
    private int yr;
    private String director;
    private int rating;

    public Movie(String title, int yr, String director, int rating) {
        super(title);
        this.title = title;
        this.yr = yr;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String title, int yr, String director) {
        super(title);
        this.yr = yr;
        this.director = director;
        this.rating = -1;
    }

    public String toString(){
        String to_return = String.format("%-30s\t%-5d\t%-30s\t",
                this.title, this.yr, this.director);
        if(this.rating == -1){
            to_return += "NA";
        }
        else {
            to_return += String.format("%-3d", this.rating);
        }
        return to_return;
    }
}
