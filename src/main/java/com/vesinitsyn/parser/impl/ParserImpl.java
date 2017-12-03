package com.vesinitsyn.parser.impl;

import com.vesinitsyn.parser.api.OperationsProvider;
import com.vesinitsyn.parser.api.Parser;
import com.vesinitsyn.parser.helper.OperationsHelper;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class ParserImpl implements Parser {

    private OperationsProvider operationsProvider;

    public ParserImpl(OperationsProvider operationsProvider) {
        this.operationsProvider = operationsProvider;
    }

    @Override
    public String parse(String expression) {
        expression = expression.replaceAll("\\s+","");
        return parseExpression(expression);
    }

    /**
     * Parse expression and returns its results.
     */
    private String parseExpression(String expression) {
        Deque<String> numbers = new LinkedList<>();
        Deque<String> signs = new LinkedList<>();
        char[] symbols = expression.toCharArray();

        for (Integer i = 0; i < symbols.length; i++) {
            StringBuilder s = new StringBuilder(Character.toString(symbols[i]));

            if (s.toString().equals("(")) {
                i = cutExpressionInBraces(symbols, i, numbers);

                if (isMultiplyOrDeleteOperation(signs)) {
                    calculateMultiplyOrDelete(numbers, signs);
                }
                continue;
            }

            if (OperationsHelper.isSign(s.toString())) {
                signs.addFirst(s.toString());
            } else {
                i = cutNumber(s, symbols, i, numbers);

                if (isMultiplyOrDeleteOperation(signs)) {
                    calculateMultiplyOrDelete(numbers, signs);
                }
            }
        }

        while (!signs.isEmpty()) {
            calculate(numbers, signs);
        }

        return numbers.poll();
    }

    /**
     * Pull out expression in braces from given array of chars.
     *
     * @param symbols array of chars that represents expression.
     * @param i       index of first brace.
     * @return expression between first and last brace.
     */
    private Integer cutExpressionInBraces(char[] symbols, int i, Deque<String> numbers) {
        int counter = 1;

        int j = i + 1;

        for (; j < symbols.length; j++) {
            if (symbols[j] == '(') {
                counter++;
            } else if (symbols[j] == ')') {
                counter--;
            }

            if (counter == 0) {
                break;
            }
        }
        numbers.addFirst(parseExpression(new String(Arrays.copyOfRange(symbols, i + 1, j))));

        return j;
    }

    /**
     * Calculate multiply of delete (depends on the first element in deque of signs)
     * operation between the two first elements in the given deque of numbers.
     */
    private void calculateMultiplyOrDelete(Deque<String> numbers, Deque<String> signs) {
        String b = numbers.pollFirst();
        String a = numbers.pollFirst();

        numbers.addFirst(operationsProvider.provide(a, b, signs.pollFirst()));
    }

    /**
     * Calculate plus or minus operation.
     */
    private void calculate(Deque<String> numbers, Deque<String> signs) {
        String a = numbers.pollLast();
        String b = numbers.pollLast();

        numbers.addLast(operationsProvider.provide(a, b, signs.pollLast()));
    }

    /**
     * Pull out number from given char array.
     * @param s string that contains number
     * @param symbols expression to be parsed.
     * @param indexOfFirstDigit position of the first digit of number in an array of chars.
     * @param numbers deque with numbers from expression.
     * @return the first index after the last digit of the number.
     */
    private Integer cutNumber(StringBuilder s, char[] symbols, Integer indexOfFirstDigit, Deque<String> numbers) {
        String temp;

        while (indexOfFirstDigit + 1 < symbols.length &&
                OperationsHelper.isNumber(temp = Character.toString(symbols[indexOfFirstDigit + 1]))) {
            s.append(temp);
            indexOfFirstDigit++;
        }

        numbers.addFirst(s.toString());

        return indexOfFirstDigit;
    }

    /**
     * Check whether current operation is delete or multiply.
     */
    private boolean isMultiplyOrDeleteOperation(Deque<String> signs) {
        return !signs.isEmpty() && (signs.peekFirst().equals("*") || signs.peekFirst().equals("/"));
    }
}
