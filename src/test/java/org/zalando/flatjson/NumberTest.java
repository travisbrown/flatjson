package org.zalando.flatjson;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class NumberTest {

    @Test public void parseZero() {
        Json json = Json.parse("0");
        assertTrue(json.isNumber());
        assertEquals(0, json.asLong());
    }

    @Test public void parseZeroWithExponent() {
        Json json = Json.parse("0e-23");
        assertTrue(json.isNumber());
        assertEquals(0, json.asDouble(), 0);
    }

    @Test(expected = ParseException.class)
    public void parseMinus() {
        Json.parse("-");
    }

    @Test public void parseNegativeZero() {
        Json json = Json.parse("-0");
        assertTrue(json.isNumber());
        assertEquals(0, json.asLong());
    }

    @Test public void parseNegativeZeroWithExponent() {
        Json json = Json.parse("-0e-2");
        assertTrue(json.isNumber());
        assertEquals(0, json.asDouble(), 0);
    }

    @Test public void parseSingleDigit() {
        Json json = Json.parse("3");
        assertTrue(json.isNumber());
        assertEquals(3, json.asLong());
    }

    @Test public void parseSingleDigitWithExponent() {
        Json json = Json.parse("3e+7");
        assertTrue(json.isNumber());
        assertEquals(3e+7, json.asDouble(), 0);
    }

    @Test(expected = ParseException.class)
    public void parseNumberWithLeadingZero() {
        Json.parse("023");
    }

    @Test public void parseInteger() {
        Json json = Json.parse("123");
        assertTrue(json.isNumber());
        assertEquals(123, json.asInt());
    }

    @Test public void parseLong() {
        Json json = Json.parse("100000000000000023");
        assertTrue(json.isNumber());
        assertEquals(100000000000000023L, json.asLong());
    }

    @Test public void parseBigDecimal() {
        Json json = Json.parse("3.141592653589793238462643383279502884197169399375105820974944592307816406286");
        assertTrue(json.isNumber());
        assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286"), json.asBigDecimal());
    }

    @Test public void parseBigInteger() {
        Json json = Json.parse("141592653589793238462643383279502884197169399375105820974944592307816406286");
        assertTrue(json.isNumber());
        assertEquals(new BigInteger("141592653589793238462643383279502884197169399375105820974944592307816406286"), json.asBigInteger());
    }

    @Test public void parseNegativeNumber() {
        Json json = Json.parse("-23");
        assertTrue(json.isNumber());
        assertEquals(-23, json.asLong());
    }

    @Test public void parseNegativeNumberWithExponent() {
        Json json = Json.parse("-2e-2");
        assertTrue(json.isNumber());
        assertEquals(-2e-2, json.asDouble(), 0);
    }

    @Test public void parseNegativeLongNumber() {
        Json json = Json.parse("-234567898765432");
        assertTrue(json.isNumber());
        assertEquals(-234567898765432L, json.asLong());
    }

    @Test public void parseNumberWithExponent() {
        Json json = Json.parse("33e12");
        assertTrue(json.isNumber());
        assertEquals(33e12, json.asDouble(), 0);
    }

    @Test public void parseNumberWithExponentUppercase() {
        Json json = Json.parse("33E12");
        assertTrue(json.isNumber());
        assertEquals(33e12, json.asDouble(), 0);
    }

    @Test public void parseNumberWithExponentPlus() {
        Json json = Json.parse("33E+12");
        assertTrue(json.isNumber());
        assertEquals(33e12, json.asDouble(), 0);
    }

    @Test public void parseNumberWithExponentMinus() {
        Json json = Json.parse("33E-12");
        assertTrue(json.isNumber());
        assertEquals(33e-12, json.asDouble(), 0);
    }

    @Test(expected = ParseException.class)
    public void parseNumberWithEmptyExponent() {
        Json.parse("33E");
    }

    @Test(expected = ParseException.class)
    public void parseNumberWithEmptyExponentPlus() {
        Json.parse("33E+");
    }

    @Test(expected = ParseException.class)
    public void parseNumberWithBrokenExponent() {
        Json.parse("33E++2");
    }

    @Test(expected = ParseException.class)
    public void parseNumberWithMultipleExponents() {
        Json.parse("33E2E4");
    }

    @Test public void parseFloat() {
        Json json = Json.parse("3.141");
        assertTrue(json.isNumber());
        assertEquals(3.141, json.asFloat(), 0.001);
    }

    @Test public void parseNegativeFloat() {
        Json json = Json.parse("-3.141");
        assertTrue(json.isNumber());
        assertEquals(-3.141, json.asFloat(), 0.001);
    }

    @Test public void parseFloatWithExponent() {
        Json json = Json.parse("-3.141e+4");
        assertTrue(json.isNumber());
        assertEquals(-3.141e4, json.asFloat(), 0.001);
    }

    @Test public void parseFloatWithLeadingZero() {
        Json json = Json.parse("0.33333333");
        assertTrue(json.isNumber());
        assertEquals(0.33333333, json.asFloat(), 0.001);
    }

    @Test public void parseFloatWithLeadingZeroAndExponent() {
        Json json = Json.parse("0.333e4");
        assertTrue(json.isNumber());
        assertEquals(0.333e4, json.asFloat(), 0.001);
    }

    @Test(expected = ParseException.class)
    public void parseFloatWithComma() {
        Json.parse("3,141");
    }

    @Test(expected = ParseException.class)
    public void parseFloatStartingWithDot() {
        Json.parse(".141");
    }

    @Test(expected = ParseException.class)
    public void parseNegativeFloatStartingWithDot() {
        Json.parse("-.141");
    }

    @Test(expected = ParseException.class)
    public void parseFloatWithDoubleDot() {
        Json.parse("111..333");
    }

}
