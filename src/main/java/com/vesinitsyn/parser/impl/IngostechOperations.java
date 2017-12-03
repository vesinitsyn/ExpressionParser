package com.vesinitsyn.parser.impl;

import com.vesinitsyn.parser.api.OperationsProvider;

public class IngostechOperations extends OperationsProvider {
    @Override
    public String add(String a, String b) {
        return a + b;
    }

    @Override
    public String minus(String a, String b) {
        return a.endsWith(b) ? a.substring(0, a.length() - b.length()) : a;
    }

    @Override
    public String multiply(String a, String b) {

        int length = Math.min(a.length(), b.length());
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(aArray[i]);
            stringBuilder.append(bArray[i]);
        }

        if (a.length() > b.length()) {
            stringBuilder.append(a.substring(length, a.length()));
        } else if (b.length() > a.length()) {
            stringBuilder.append(b.substring(length, b.length()));
        }

        return stringBuilder.toString();
    }

    @Override
    public String divide(String a, String b) {
        char[] bArray = b.toCharArray();

        StringBuilder aStringBuilder = new StringBuilder(a);

        int deletedAmount = 0;

        for (int i = 0; i < b.length(); i++) {
            int aIndex = i * 2 + 1;

            if (aIndex < a.length() && a.charAt(aIndex) == bArray[i]) {
                aStringBuilder.deleteCharAt(aIndex - deletedAmount);
                deletedAmount++;
            }
        }
        return aStringBuilder.toString();
    }


}
