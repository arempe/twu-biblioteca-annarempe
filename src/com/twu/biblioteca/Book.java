package com.twu.biblioteca;

public class Book extends Item {
    private String author;
    private int pub_year;

    public Book(String title, String author, int pub_year){
        super(title);
        this.author = author;
        this.pub_year = pub_year;
    }
    public String toString(){
        return String.format("%-20s\t%-20s\t%-5d",
        this.title, this.author, this.pub_year);
    }


}
