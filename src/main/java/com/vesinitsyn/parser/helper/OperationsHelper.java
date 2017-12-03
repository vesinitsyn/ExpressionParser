package com.vesinitsyn.parser.helper;

import java.util.Arrays;
import java.util.List;

public class OperationsHelper {
    private static final List<String> signs = Arrays.asList("+", "-", "*", "/", "(", ")");

    public static boolean isSign(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static boolean isNumber(String s) {
        return !signs.contains(s);
    }
}
