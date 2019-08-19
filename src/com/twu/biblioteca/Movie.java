package com.twu.biblioteca;

public class Movie {
    private String title;
    private int yr;
    private String director;
    private int rating;
    private boolean status;
    private String checked_out_by;

    public Movie(String title, int yr, String director, int rating) {
        this.title = title;
        this.yr = yr;
        this.director = director;
        this.rating = rating;
        this.status = true;
        this.checked_out_by = null;
    }

    public Movie(String title, int yr, String director) {
        this.title = title;
        this.yr = yr;
        this.director = director;
        this.rating = -1;
        this.checked_out_by = null;
    }

    public String toString(){
        return String.format("%-30s\t%-5d\t%-30s\t%-3d\t\n",
                this.title, this.yr, this.director, this.rating);
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCheckedOutBy() {
        return this.checked_out_by;
    }
}
