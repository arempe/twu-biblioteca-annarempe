package com.twu.biblioteca;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> menu_opt;
    private int quit_opt;

    public Menu() {
        this.menu_opt = new ArrayList<String>();
        this.menu_opt.add("List of books");
        this.menu_opt.add("Checkout book");
        this.menu_opt.add("Check-in book");
        this.menu_opt.add("Quit");
        this.quit_opt = this.menu_opt.size();
    }

    public int getQuitOpt(){
        return quit_opt;
    }

    public ArrayList<String> getOpts() {
        return this.menu_opt;
    }
}
