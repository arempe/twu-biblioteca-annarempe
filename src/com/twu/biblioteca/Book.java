package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private int pub_year;

    public Book(String title, String author, int pub_year){
        this.title = title;
        this.author = author;
        this.pub_year = pub_year;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void printBook(){
        System.out.printf("%-20s\t%-20s\t%-5d\n",
                this.title, this.author, this.pub_year);
    }
}
