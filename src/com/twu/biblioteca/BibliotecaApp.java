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
    private String check_out_header_msg = "Please select the number next to the book you want to checkout\n";
    private String check_in_header_msg = "Please enter the title of the book you are checking in\n";
    private String check_in_success_msg = "Thank you for returning the book\n";
    private String check_in_failure_msg = "That is not a valid book to return\n";
    private String movie_check_out_header_msg = "Please select the number next to the movie you want to checkout\n";
    private String lib_num_prompt = "Please enter your library number in the form XXX-XXXX\n";
    private String pass_prompt = "Please enter your password\n";
    private String invalid_login_prompt = "Sorry, incorrect login information\n";


    protected BibliotecaApp(PrintStream out, InputWrapper in_wrap){
        this.out = out;
        this.in_wrap = in_wrap;
        this.logged_in = false;
        setupAccounts();
        setupMenu();
        setupBookInv();
        setupMovieInv();

    }

    private void setupAccounts() {
        this.accounts = new Accounts();
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

    private void setupMovieInv() {
        this.movie_inv = new MovieInventory(this.out);
    }

    private void setupBookInv() {
        this.book_inv = new BookInventory(this.out);
    }

    private void displayWelcomeMsg(){
        this.out.println("Welcome to Biblioteca. " +
                "You're one-stop-shop for great book titles in Bangalore!");
    }

    protected void onRun(){
        displayWelcomeMsg();
    }

    protected  void openMenu(){
        displayMenu();
        int selection = this.in_wrap.getInt();
        openMenu(selection);
    }
    protected void openMenu(int selection) {
        while(selection != this.quit_opt){
            if(selection == 1){
                displayBookInv();
            }
            else if(selection == 2){
                checkOutBook();
            }
            else if(selection == 3){
                checkInBook();
            }
            else if(selection == 4){
                displayMovieInventory();
            }
            else if(selection == 5){
                checkOutMovie();
            }
            else{//invalid input
                this.out.print(this.invalid_option_msg);
            }
            displayMenu();
            selection = this.in_wrap.getInt();
        }
        onQuit();
    }


    private void displayMovieInventory() {
        this.movie_inv.displayMovieInventory();
    }

    private void checkInBook() {
        this.out.print(this.check_in_header_msg);
        String book_title = this.in_wrap.getString();
        boolean success = checkInBook(book_title);
        if(success){
            this.out.print(this.check_in_success_msg);
        }
        else{
            this.out.print(this.check_in_failure_msg);
        }
    }

    public boolean checkInBook(String book_title) {
        return(this.book_inv.checkInBook(book_title));

    }

    private void checkOutMovie() {
        this.out.print(this.movie_check_out_header_msg);
        displayMovieInventory();
        this.out.printf("%d)\tBack\n", this.movie_inv.getNumCheckedIn() + 1);

        int selection = this.in_wrap.getInt();

        if(selection == this.movie_inv.getNumCheckedIn() + 1){
            //return to menu
        }
        else if(selection < 1 || selection > this.movie_inv.getNumCheckedIn()){
            this.out.println("Sorry, that movie is not available");
        }
        else{
            checkOutMovie(selection);
        }
    }

    private void checkOutMovie(int selection) {
        if(!this.logged_in){
            promptLogin();
        }

        if(this.logged_in){
            if(this.movie_inv.checkOutMovie(selection));
            {
                this.out.println("Thank you! Enjoy the book");
            }
        }
        else{
            this.out.print(this.invalid_login_prompt);
        }
    }

    protected String checkOutBook() {
        this.out.print(this.check_out_header_msg);
        displayBookInv();
        this.out.printf("%d)\tBack\n", this.book_inv.getNumCheckedIn() + 1);

        int selection = this.in_wrap.getInt();

        if(selection == this.book_inv.getNumCheckedIn() + 1){
            //return to menu
        }
        else if(selection < 1 || selection > this.book_inv.getNumCheckedIn()){
            this.out.println("Sorry, that book is not available");
        }
        else{

            if(!this.logged_in){
                promptLogin();
            }

            if(this.logged_in){
                checkOutBook(selection);
                this.out.println("Thank you! Enjoy the book");
            }
            else{
                this.out.print(this.invalid_login_prompt);
            }
        }
        return this.current_lib_num;
    }

    boolean promptLogin() {
        this.out.print(this.lib_num_prompt);
        String libnum = this.in_wrap.getString().trim();
        this.out.print(this.pass_prompt);
        String password = this.in_wrap.getString().trim();
        this.logged_in = this.accounts.login(libnum, password);
        return logged_in;
    }

    protected void checkOutBook(Integer selection) {
        this.book_inv.checkOutBook(selection);
    }

    private void displayMenu(){
        this.out.println(this.menuToString());
    }

    public void displayBookInv(){
        this.book_inv.displayBookInventory();
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
        return this.check_in_header_msg;
    }

    public String getCheckInFailMsg() {
        return this.check_in_failure_msg;
    }

    public String getMovieInvHeader() {
        return this.movie_inv.getInvHeader();
    }

    public String getCheckInMovieHeader() {
        return this.movie_check_out_header_msg;
    }

    public boolean getLoginStatus() {
        return this.logged_in;
    }

    public String getCheckedInUsr() {
        return this.current_lib_num;
    }

    public String getUserCheckedOut(String Title) {
        Book book = this.book_inv.searchInv(Title);
        Movie movie;
        String lib_num = null;
        if(book != null){
            lib_num = book.getCheckedOutBy();
        }
        else{
            movie = this.movie_inv.searchInv(Title);
                if(movie != null){
                    lib_num = movie.getCheckedOutBy();
                }
            }
        return lib_num;
    }
}
