package com.vesinitsyn.parser;

import com.vesinitsyn.parser.api.Parser;
import com.vesinitsyn.parser.impl.IngostechOperations;
import com.vesinitsyn.parser.impl.ParserImpl;

public class Main {
    private static Parser parser = new ParserImpl(new IngostechOperations());

    public static void main(String[] args) {
        System.out.println(">>> " + args[0]);
        System.out.println("<<< " + parser.parse(args[0]));
    }
}
