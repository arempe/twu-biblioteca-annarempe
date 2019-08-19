package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private BibliotecaApp biblioteca_app;
    private Book b1, b2, b3;
    private Movie m1;
    private Account usr1;

    @Before
    public void setUp() {
        b1 = new Book("The Name of the Wind", "Patrick Rothfuss", 2007);
        b2 = new Book("Green Eggs and Ham", "Dr. Seuss", 1960);
        b2.setStatus(false);
        b3 = new Book("Milk and Honey", "Rupi Kaur", 2015);
        m1 = new Movie("Moonlight", 2016, "Barry Jenkins", 7);
        usr1 = new Account("111-1111", "pass", "Jon Doe", "555-555-5555", "xxx@xxx.com");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testOnRun() {

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);
        InputWrapper input_wrapper = new InputWrapper();
        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper);


        biblioteca_app.onRun();
        assertThat(output_stream.toString(), is("Welcome to Biblioteca. You're one-stop-shop for great book titles in Bangalore!\n"));

    }

    @Test
    public void testOpenMenu() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(this.biblioteca_app.getQuitOpt());

        biblioteca_app.openMenu();

        String expected_str = biblioteca_app.menuToString() + "\n" + biblioteca_app.getQuitMsg();

        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testSelectDisplayBookInv() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(1, this.biblioteca_app.getQuitOpt());

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() +
                        "\n\t%-20s\t%-20s\t%-5s\n" + "1)\t"
                        + this.b1.toString() + "\n2)\t" + this.b3.toString() + "\n"
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg(),
                "Title", "Author", "Year");

        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testSelectInvalidMenuOpt() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(-1, this.biblioteca_app.getQuitOpt());

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() +
                        "\n" + biblioteca_app.getInvalidMsg() +
                        biblioteca_app.menuToString() +
                        "\n" + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckInBookOption() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(3, this.biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn( "Green Eggs and Ham", "111-1111", "pass");

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getCheckInMsg()
                        + biblioteca_app.getLibNumPromptHeader()
                        + biblioteca_app.getPassPrompt()
                        + biblioteca_app.getCheckInSuccessMsg()
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckInBookSuccess() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(3, 1, this.biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn("Green Eggs and Ham", "111-1111", "pass");

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getCheckInMsg()
                        + biblioteca_app.getLibNumPromptHeader()
                        + biblioteca_app.getPassPrompt()
                        + biblioteca_app.getCheckInSuccessMsg()
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getInvHeader()
                        + "1)\t" + b1.toString()
                        + "\n2)\t" + b2.toString()
                        + "\n3)\t" + b3.toString() + "\n"
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckInFailBook() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(3, this.biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass", "Green Eggs and Ha");

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getCheckInMsg()
                        + biblioteca_app.getLibNumPromptHeader()
                        + biblioteca_app.getPassPrompt()
                        + biblioteca_app.getCheckInFailMsg()
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckOutBook() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(2, 1, 1, this.biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");
        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getCheckOutMsg()
                        + biblioteca_app.getInvHeader()
                        + "1)\t" + b1.toString() + "\n"
                        + "2)\t" + b3.toString() + "\n"
                        + "3)\tBack\n"
                        + biblioteca_app.getLibNumPromptHeader()
                        + biblioteca_app.getPassPrompt()
                        + "Thank you! Enjoy the book\n"
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getInvHeader()
                        + "1)\t" + b3.toString() + "\n"
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testListMovieOption() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(4, this.biblioteca_app.getQuitOpt());

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getMovieInvHeader()
                        + "1)\t" + m1.toString()
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckOutMovieSuccess() {
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(5, 1, 4, this.biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");

        biblioteca_app.openMenu();
        String expected_str = String.format(
                biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getCheckInMovieHeader()
                        + biblioteca_app.getMovieInvHeader()
                        + "1)\t" + m1.toString()
                        + "2)\tBack\n"
                        + "Please enter your library number in the form XXX-XXXX\n"
                        + "Please enter your password\n"
                        + "Thank you! Enjoy the movie\n"
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getMovieInvHeader()
                        + biblioteca_app.menuToString() + "\n"
                        + biblioteca_app.getQuitMsg()
        );
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testLoginSuccess(){
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");
        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        assertThat(biblioteca_app.getLoginStatus(), is(false));

        biblioteca_app.promptLogin();

        assertThat(biblioteca_app.getLoginStatus(), is(true));


    }

    @Test
    public void testTrackCheckoutBookLibNum(){
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);

        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");
        when(input_wrapper_mock.getInt()).thenReturn(2, 1, biblioteca_app.getQuitOpt());



        biblioteca_app.checkOutBook(1, "111-1111");
        String lib_num = biblioteca_app.getUserCheckedOut("The Name of the Wind");
        assertThat(lib_num, is("111-1111"));
    }

    @Test
    public void testTrackCheckoutMovieLibNum(){
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);

        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");
        when(input_wrapper_mock.getInt()).thenReturn(4, 1, biblioteca_app.getQuitOpt());



        biblioteca_app.checkOutMovie(1, "111-1111");
        String lib_num = biblioteca_app.getUserCheckedOut("Moonlight");
        assertThat(lib_num, is("111-1111"));
    }

    @Test
    public void testLoginCheckInMovie(){
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        assertThat(biblioteca_app.getCheckedInStatus("Warrior"), is(false));

        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");

        biblioteca_app.checkInMovie("Warrior");
        assertThat(biblioteca_app.getCheckedInStatus("Warrior"), is(true));

    }

    @Test
    public void testDisplayUsrInfo(){
        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(7, biblioteca_app.getQuitOpt());
        when(input_wrapper_mock.getString()).thenReturn("111-1111", "pass");

        biblioteca_app.openMenu();
        String expected = biblioteca_app.menuToString() + "\n"
                + biblioteca_app.getLibNumPromptHeader()
                + biblioteca_app.getPassPrompt()
                + usr1.toString()
                + biblioteca_app.menuToString() + "\n"
                + biblioteca_app.getQuitMsg();
        assertThat(output_stream.toString(), is(expected));
    }

}