package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestRule;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BibliotecaAppTest {
    private BibliotecaApp biblioteca_app;
    private Book b1, b2, b3;

    @Before
    public void setUp() {
        b1 = new Book("The Name of the Wind", "Patrick Rothfuss", 2007);
        b2 = new Book("Green Eggs and Ham", "Dr. Seuss", 1960);
        b2.setStatus(false);
        b3 = new Book("Milk and Honey", "Rupi Kaur", 2015);

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
    public void testSelectDisplayBookInv() {

        InputWrapper input_wrapper_mock = mock(InputWrapper.class);

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);

        biblioteca_app = new BibliotecaApp(print_stream, input_wrapper_mock);
        when(input_wrapper_mock.getInt()).thenReturn(1, 4);

        biblioteca_app.openMenu();
        String expected_str = String.format(
                        biblioteca_app.menuToString() +
                        "\t%-20s\t%-20s\t%-5s\n" + "1)\t"
                        + this.b1.toString() + "\n2)\t" + this.b3.toString() + "\n"
                        + biblioteca_app.menuToString() +
                        "Thanks for using Biblioteca!\n",
                        "Title", "Author", "Year");
        assertThat(output_stream.toString(), is(expected_str));
    }

    @Test
    public void testCheckInBook() {
    }

    @Test
    public void testCheckOutBook() {
    }

    @Test
    public void testDisplayBookInv() {
    }
}
