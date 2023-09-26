package com.bank.utils;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner scanner = null;

    private ScannerUtils(){}

    public static Scanner getScannerInstance(){
        if(scanner == null) {
            initialize();
        }

        return scanner;
    }

    public static Scanner initialize() {
        scanner = new Scanner(System.in);
        return scanner;
    }
}
