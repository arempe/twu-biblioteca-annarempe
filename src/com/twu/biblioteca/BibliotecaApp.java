package com.twu.biblioteca;


import java.io.PrintStream;
import java.util.ArrayList;

public class BibliotecaApp {

    private BookInventory book_inv;
    private MovieInventory movie_inv;

    private ArrayList<String> menu_opt;
    private int quit_opt;
    private PrintStream out;
    private InputWrapper in_wrap;

    private Accounts accounts;
    private boolean logged_in;

    private String current_lib_num;

    // msgs
    private String invalid_option_msg = "Please select a valid option!\n";
    private String quit_msg = "Thank you for using BibliotecaApp!\n";
    private String check_out_header_msg = "Please select the number next to the item you want to checkout\n";
    private String check_in_success_msg = "Thank you for returning this item\n";
    private String check_in_failure_msg = "That is not a valid item to return\n";
    private String lib_num_prompt = "Please enter your library number in the form XXX-XXXX\n";
    private String pass_prompt = "Please enter your password\n";
    private String invalid_login_prompt = "Sorry, incorrect login information\n";
    private String check_in_header = "Please enter the title of the item you are checking in\n";
    protected String check_out_success_msg = "Thank you, enjoy!\n";

    protected BibliotecaApp(PrintStream out, InputWrapper in_wrap){
        this.out = out;
        this.in_wrap = in_wrap;
        this.logged_in = false;
        this.accounts = new Accounts();
        this.book_inv = new BookInventory(this.out);
        this.movie_inv = new MovieInventory(this.out);
        setupMenu();
    }

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        InputWrapper input_wrapper = new InputWrapper();
        BibliotecaApp biblioteca_app = new BibliotecaApp(out, input_wrapper);
        biblioteca_app.onRun();
        biblioteca_app.openMenu();
    }

    private void setupMenu() {
        this.menu_opt = new ArrayList<String>();
        this.menu_opt.add("List of books");
        this.menu_opt.add("Checkout book");
        this.menu_opt.add("Check-in book");
        this.menu_opt.add("List of movies");
        this.menu_opt.add("Checkout movie");
        this.menu_opt.add("Check-in movie");
        this.menu_opt.add("Display user info");
        this.menu_opt.add("Quit");
        this.quit_opt = this.menu_opt.size();
    }

    public String menuToString(){
        StringBuilder to_return;
        to_return = new StringBuilder("\nPlease select from the following options\n");
        int i = 0;
        for(String menu_opt: this.menu_opt){
            i++;
            to_return.append(String.format("%d)\t%s\n", i, menu_opt));
        }
        return to_return.toString();
    }

    private void displayWelcomeMsg(){
        this.out.println("Welcome to Biblioteca. " +
                "You're one-stop-shop for great book titles in Bangalore!");
    }

    protected void onRun(){
        displayWelcomeMsg();
    }

    protected void openMenu(){
        displayMenu();
        int selection = this.in_wrap.getInt();
        openMenu(selection);
    }

    protected void openMenu(int selection) {
        while(selection != this.quit_opt){
            if(selection == 1){
                this.book_inv.displayInventory();
            }
            else if(selection == 2){
                checkOutItem("Book");
            }
            else if(selection == 3){
                checkInItem("Book");
            }
            else if(selection == 4){
                this.movie_inv.displayInventory();
            }
            else if(selection == 5){
                checkOutItem("Movie");
            }
            else if(selection == 6){
                checkInItem("Movie");
            }
            else if(selection == 7){
                displayUsrInfo();
            }
            else{//invalid input
                this.out.print(this.invalid_option_msg);
            }
            displayMenu();
            selection = this.in_wrap.getInt();
        }
        onQuit();
    }

    private void displayUsrInfo() {
        if(!this.logged_in){
            promptLogin();
        }
        if(this.logged_in){
            this.out.print(this.accounts.searchUsrs(this.current_lib_num).toString());
        }
    }

    protected void checkInItem(String type) {
        this.out.print(this.check_in_header);
        String title = this.in_wrap.getString();
        boolean success = false;

        if(!this.logged_in){
            promptLogin();
        }

        if(logged_in) {
            if(type.toLowerCase().equals("movie")) {
                success = this.movie_inv.checkInItem(title);
            }
            else
            {
                success = this.book_inv.checkInItem(title);
            }
        }

        if(success){
            this.out.print(this.check_in_success_msg);
        }
        else
        {
            this.out.print(this.check_in_failure_msg);
        }
    }

    private void checkOutItem(String type) {
        this.out.print(this.check_out_header_msg);
        int num_checked_in = -1;
        if(type.toLowerCase().equals("movie")){
            this.movie_inv.displayInventory();
            num_checked_in = this.movie_inv.getNumCheckedIn();
        }
        else{
            this.book_inv.displayInventory();
            num_checked_in = this.book_inv.getNumCheckedIn();
        }

        this.out.printf("%d)\tBack\n", num_checked_in + 1);

        int selection = this.in_wrap.getInt();

        if(selection == num_checked_in + 1){
            //return to menu
        }
        else if(selection < 1 || selection > num_checked_in){
            this.out.println("Sorry, that movie is not available");
        }
        else{
            if(type.toLowerCase().equals("movie")) {
                checkOutItem(selection, this.current_lib_num, "movie");
            }
            else{
                checkOutItem(selection, this.current_lib_num, "book");
            }
        }
    }
    
    protected void checkOutItem(Integer selection, String lib_num, String type) {
        if(!this.logged_in){
            promptLogin();
        }
        if(this.logged_in){
            boolean check_out_success = false;
            if(type.toLowerCase().equals("movie")){
                check_out_success = this.movie_inv.checkOutItem(selection, lib_num);
            }
            else{
                check_out_success = this.book_inv.checkOutItem(selection, lib_num);
            }

            if(check_out_success)
            {
                this.out.print(this.check_out_success_msg);
            }
        }
        else{
            this.out.print(this.invalid_login_prompt);
        }
    }

    boolean promptLogin() {
        this.out.print(this.lib_num_prompt);
        String libnum = this.in_wrap.getString().trim();
        this.out.print(this.pass_prompt);
        String password = this.in_wrap.getString().trim();
        this.logged_in = this.accounts.login(libnum, password);
        if(logged_in){
            this.current_lib_num = libnum;
        }
        return logged_in;
    }

    private void displayMenu(){
        this.out.println(this.menuToString());
    }

    private void onQuit(){
        this.out.print(this.quit_msg);
    }

    protected int getQuitOpt(){
        return this.quit_opt;
    }
    protected String getQuitMsg(){
        return this.quit_msg;
    }

    public String getInvalidMsg() {
        return this.invalid_option_msg;
    }
    public String getCheckOutMsg(){
        return this.check_out_header_msg;
    }

    public String getInvHeader() {
        return this.book_inv.getInvHeaderMsg();
    }

    public String getCheckInSuccessMsg(){
        return this.check_in_success_msg;
    }

    public String getCheckInMsg() {
        return this.check_in_header;
    }

    public String getCheckInFailMsg() {
        return this.check_in_failure_msg;
    }

    public String getMovieInvHeader() {
        return this.movie_inv.getInvHeaderMsg();
    }

    public String getCheckInMovieHeader() {
        return this.check_out_header_msg;
    }

    public String getLibNumPromptHeader(){
        return this.lib_num_prompt;
    }

    public String getPassPrompt(){
        return this.pass_prompt;
    }

    public boolean getLoginStatus() {
        return this.logged_in;
    }

    public String getUserCheckedOut(String title) {
        Item book = this.book_inv.searchInv(title);
        Item movie;
        String lib_num = null;
        if(book != null){
            lib_num = book.getCheckedOutBy();
        }
        else{
            movie = this.movie_inv.searchInv(title);
                if(movie != null){
                    lib_num = movie.getCheckedOutBy();
                }
            }
        return lib_num;
    }

    public boolean getCheckedInStatus(String title) {
        Item book = this.book_inv.searchInv(title);
        Item movie;
        boolean status = false;

        if(book != null){
            status = book.getStatus();
        }
        else{
            movie = this.movie_inv.searchInv(title);
            if(movie != null){
                status = movie.getStatus();
            }
        }
        return status;
    }
}