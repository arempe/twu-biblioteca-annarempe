package com.twu.biblioteca;

import java.util.Scanner;

public class InputWrapper {
    public int getInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
