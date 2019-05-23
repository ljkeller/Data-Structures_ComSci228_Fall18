package edu.iastate.cs228.hw01;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the MyString class.
 * @author Kyle J Vetsch
 * @version 1.0
 */
public class MyStringTest {
    /** Return values should not reference the initializing array. */
    @Test
    public void memoryMyString() {
        char[] str = new char[]{'a', 'b', 'c'};

        MyString s = new MyString(str);

        assertNotSame(s.toCharArray(), str);
    }

    /** Should point to differing memory and be separate objects. */
    @Test
    public void sameMyString() {
        assertNotSame(new MyString(new char[]{'a', 'b'}),
                new MyString(new char[]{'a', 'b'}));
        assertNotEquals(new MyString(new char[]{'a', 'b'}),
                new MyString(new char[]{'a', 'b'}));
    }

    /** Throw exception on null array. */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionNullMyString() {
        new MyString(null);
    }

    /** Throw exception on empty array. */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionEmptyMyString() {
        new MyString(new char[]{});
    }

    /** Verify get length. */
    @Test
    public void length() {
        char[] str = new char[]{'a', 'b', 'c'};

        MyString s = new MyString(str);

        assertEquals(3, s.length());
    }

    /** Verify get char. */
    @Test
    public void charAt() {
        char[] str = new char[]{'a', 'b', 'c'};

        MyString s = new MyString(str);

        assertEquals('a', s.charAt(0));
        assertEquals('b', s.charAt(1));
        assertEquals('c', s.charAt(2));
    }

    /** Throw exception when out of bounds (under bounds). */
    @Test(expected = IndexOutOfBoundsException.class)
    public void exceptionUnderCharAt() {
        char[] str = new char[]{'a', 'b', 'c'};

        MyString s = new MyString(str);

        assertEquals('a', s.charAt(-1));
    }

    /** Throw exception when out of bounds (over bounds). */
    @Test(expected = IndexOutOfBoundsException.class)
    public void exceptionOverCharAt() {
        char[] str = new char[]{'a', 'b', 'c'};

        MyString s = new MyString(str);

        assertEquals('a', s.charAt(15));
    }

    /** Verify correct substring. */
    @Test
    public void substring() {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        MyString s = new MyString(str);

        char[] substr = new char[]{'l', 'o', ' ', 'w', 'o', 'r'};

		assertArrayEquals(substr, s.substring(3, 9).toCharArray());
    }

    /** Verify doesn't work with specifying single location. */
    @Test(expected = IndexOutOfBoundsException.class)
    public void singleSubstring() {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        MyString s = new MyString(str);

        char[] substr = new char[]{};

        assertArrayEquals(substr, s.substring(3, 3).toCharArray());
    }

    /** Throws exception when first parameter is under (per Java spec). */
    @Test(expected = IndexOutOfBoundsException.class)
    public void underSubstring() {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        MyString s = new MyString(str);

        char[] substr = new char[]{'l', 'o', ' ', 'w', 'o', 'r'};

        assertArrayEquals(substr, s.substring(-1, 9).toCharArray());
    }

    /** Throws exception when second parameter is over (per Java spec). */
    @Test(expected = IndexOutOfBoundsException.class)
    public void overSubstring() {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        MyString s = new MyString(str);

        char[] substr = new char[]{'l', 'o', ' ', 'w', 'o', 'r'};

        assertArrayEquals(substr, s.substring(3, 15).toCharArray());
    }

    /** Throws exception when beginning bound is
     * larger than ending bound (per Java spec). */
    @Test(expected = IndexOutOfBoundsException.class)
    public void backwardsSubstring() {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        MyString s = new MyString(str);

        char[] substr = new char[]{'l', 'o', ' ', 'w', 'o', 'r'};

        assertArrayEquals(substr, s.substring(9, 3).toCharArray());
    }

    /** Verify convert string to lowercase. */
    @Test
    public void toLowerCase() {
        char[] str = new char[]{'H', 'E', 'L', 'L', 'O', ' ',
                'W', 'O', 'R', 'L', 'D', '!'};

        MyString s = new MyString(str);

        char[] lower = new char[]{'h', 'e', 'l', 'l', 'o', ' ',
                'w', 'o', 'r', 'l', 'd', '!'};

        assertArrayEquals(lower, s.toLowerCase().toCharArray());
    }

    /** Verify convert char array to int. */
    @Test
    public void valueOf() {
        char[] str = new char[]{'1', '5', '3'};
        int i = 153;

        assertArrayEquals(str, MyString.valueOf(i).toCharArray());
    }
}