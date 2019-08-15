package com.twu.biblioteca;

import java.io.PrintStream;

public class Book {
    private String title;
    private String author;
    private int pub_year;
    private boolean checked_in;
    private PrintStream out;

    public Book(String title, String author, int pub_year, PrintStream out){
        this.out = out;
        this.title = title;
        this.author = author;
        this.pub_year = pub_year;
        this.checked_in = true;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public boolean getStatus(){
        return this.checked_in;
    }

    public void setStatus(boolean status){
        this.checked_in = status;
    }

    public String toString(){
        return String.format("%-20s\t%-20s\t%-5d",
        this.title, this.author, this.pub_year);
    }
}
