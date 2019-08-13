package com.twu.biblioteca;

public class Book {
    private String title;

    public Book(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void printBook(){
        System.out.println(this.title);
    }
}
