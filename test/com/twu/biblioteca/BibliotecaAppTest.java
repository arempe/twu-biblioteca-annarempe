package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BibliotecaAppTest {
    private BibliotecaApp biblioteca_app;
    BibliotecaApp biblioteca_app_spy;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testOnRun() {

        ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
        PrintStream print_stream = new PrintStream(output_stream);
        InputWrapper input_wrapper = new InputWrapper();
        BibliotecaApp biblioteca_app = new BibliotecaApp(print_stream, input_wrapper);


        biblioteca_app.onRun();

        assertThat(output_stream.toString(), is("Welcome to Biblioteca. You're one-stop-shop for great book titles in Bangalore!\n"));

    }

    @Test
    public void testSelectDisplayBookInv(){

        InputWrapper input_wrapper = mock(InputWrapper.class);

        when(input_wrapper.getInt()).thenReturn(1);

//        verify(input_wrapper).getInt(); //verify is called
//        verify(input_wrapper, times(3)).getInt();//verify 3 times




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
