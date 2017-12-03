package com.vesinitsyn.parser.impl;

import com.vesinitsyn.parser.api.OperationsProvider;

public class MathOperations extends OperationsProvider {
    @Override
    public String add(String a, String b) {
        return String.valueOf(Float.parseFloat(a) + Float.parseFloat(b));
    }

    @Override
    public String minus(String a, String b) {
        return String.valueOf(Float.parseFloat(a) - Float.parseFloat(b));
    }

    @Override
    public String multiply(String a, String b) {
        return String.valueOf(Float.parseFloat(a) * Float.parseFloat(b));
    }

    @Override
    public String divide(String a, String b) {
        return String.valueOf(Float.valueOf(a) / Float.valueOf(b));
    }


}
