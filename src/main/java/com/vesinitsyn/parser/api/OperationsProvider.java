package com.vesinitsyn.parser.api;

public abstract class OperationsProvider {

    public abstract String add(String a, String b);

    public abstract String minus(String a, String b);

    public abstract String multiply(String a, String b);

    public abstract String divide(String a, String b);

    public  String provide(String a, String b, String sign) {

        switch (sign) {
            case "+":
                return add(a, b);
            case "-":
                return minus(a, b);
            case "*":
                return multiply(a, b);
            case "/":
                return divide(a, b);
        }

        throw new IllegalArgumentException();
    }
}
