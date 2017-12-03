package com.vesinitsyn.parser.test;


import com.vesinitsyn.parser.api.OperationsProvider;
import com.vesinitsyn.parser.api.Parser;
import com.vesinitsyn.parser.impl.IngostechOperations;
import com.vesinitsyn.parser.impl.ParserImpl;
import org.junit.Assert;
import org.junit.Test;

public class ParserTests {

    private OperationsProvider operationsProvider = new IngostechOperations();
    private Parser parser = new ParserImpl(new IngostechOperations());

    @Test
    public void testOperations() {
        Assert.assertEquals(operationsProvider.add("abc", "xyz"), "abcxyz");
        Assert.assertEquals(operationsProvider.add("xyz", "abc"), "xyzabc");

        Assert.assertEquals(operationsProvider.minus("abcxyz", "xyz"), "abc");
        Assert.assertEquals(operationsProvider.minus("abc", "xyz"), "abc");
        Assert.assertEquals(operationsProvider.minus("abcxyzd", "xyz"), "abcxyzd");

        Assert.assertEquals(operationsProvider.multiply("abc", "xyz"), "axbycz");
        Assert.assertEquals(operationsProvider.multiply("abc", "xyzps"), "axbyczps");
        Assert.assertEquals(operationsProvider.multiply("abcde", "xyz"), "axbyczde");

        Assert.assertEquals(operationsProvider.divide("abc", "xyz"), "abc");
        Assert.assertEquals(operationsProvider.divide("axbycz", "xyz"), "abc");
        Assert.assertEquals(operationsProvider.divide("axbyczps", "xyz"), "abcps");
    }

    @Test
    public void testParser() {
        String expression = "((index	- ex)	- d)	+	gst	*	osr	+	(an	+	k	+	oh)	/	(n	+	o)";

        Assert.assertEquals(parser.parse(expression), "ingosstrakh");
    }
}
