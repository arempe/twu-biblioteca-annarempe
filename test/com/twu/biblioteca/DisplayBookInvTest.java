package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DisplayBookInvTest {
    private BibliotecaApp b1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void before(){
        b1 = new BibliotecaApp();


        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void displayCheckedInBooks(){
        b1.checkOutBook(0);
        b1.printInventory();
        String expected_string = String.format("1)\t%-20s\t%-20s\t%-5d\n", "Milk and Honey", "Rupi Kaur", 2015);
        assertEquals(expected_string, outContent.toString());


    }

    @After
    public void after(){
        System.setOut(originalOut);
    }
}
