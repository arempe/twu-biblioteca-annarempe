package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CheckInTest {
    private BibliotecaApp b1;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void before(){
      //  b1 = new BibliotecaApp();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void checkInExistingBook(){
        b1.checkInBook("Green Eggs and Ham");

        b1.displayBookInv();

        String expected_string = String.format("\t%-20s\t%-20s\t%-5s\n" +
                        "1)\t%-20s\t%-20s\t%-5d\n" +
                        "2)\t%-20s\t%-20s\t%-5d\n" +
                        "3)\t%-20s\t%-20s\t%-5d\n" +
                        "", "Title", "Author", "Year",
                "The Name of the Wind", "Patrick Rothfuss", 2007,
                "Green Eggs and Ham", "Dr. Seuss", 1960,
                "Milk and Honey", "Rupi Kaur", 2015,
                        "Milk and Honey", "Rupi Kaur", 2015);
        assertEquals(expected_string, outContent.toString());


    }

    @After
    public void after(){
        System.setOut(originalOut);
    }
}
