package com.bank.utils;

import org.junit.Test;

import java.util.Scanner;

public class ScannerUtilsTest {

    @Test
    public void testGetScannerInstance() {
        final Scanner result = ScannerUtils.getScannerInstance();

    }

    @Test
    public void testInitialize() {
        final Scanner result = ScannerUtils.initialize();

    }
}
