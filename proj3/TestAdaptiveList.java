package edu.iastate.cs228.proj3;
/**
 * @author Lucas Keller
 */

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;


public class TestAdaptiveList{


    @BeforeAll
    public void setup(){
        AdaptiveList<Integer> xList = new AdaptiveList<Integer>();
        xList.add(15);
        xList.add(14);
        xList.add(13);
        xList.add(12);
    }

    @Test
    public void clear() {
    }

    /**
     * Tests which methods are affecting linkedUTD status and which are not.
     */
    @org.junit.Test
    public void getlinkedUTD() {
        AdaptiveList<Integer> testUTD = new AdaptiveList<Integer>();
        assertTrue(testUTD.getlinkedUTD());
        testUTD.add(9);
        assertTrue(testUTD.getlinkedUTD());
        testUTD.set(0, 10);
        assertFalse(testUTD.getlinkedUTD());
        ArrayList<Integer> aList = new ArrayList<>();
        aList.add(99);
        aList.add(99);
        testUTD.addAll(aList);
        assertTrue(testUTD.getlinkedUTD());
        testUTD.set(1, 15);
        testUTD.addAll(1, aList);
        assertTrue(testUTD.getlinkedUTD());
        testUTD.reverse();
        assertFalse(testUTD.getlinkedUTD());
        testUTD.add(10);
        testUTD.reorderOddEven();
        assertFalse(testUTD.getlinkedUTD());

    }

    /**
     * Tests which methods are affecting arrayUTD status and which are not
     */
    @org.junit.Test
    public void getarrayUTD() {
        AdaptiveList<Integer> testUTD = new AdaptiveList<Integer>();
        assertFalse(testUTD.getarrayUTD());
        testUTD.add(9);
        assertFalse(testUTD.getarrayUTD());
        testUTD.set(0, 10);
        assertTrue(testUTD.getarrayUTD());
        ArrayList<Integer> aList = new ArrayList<>();
        aList.add(99);
        aList.add(99);
        testUTD.addAll(aList);
        assertFalse(testUTD.getarrayUTD());
        testUTD.set(1, 15);
        testUTD.addAll(1, aList);
        assertFalse(testUTD.getarrayUTD());
        testUTD.reverse();
        assertTrue(testUTD.getarrayUTD());
        testUTD.add(10);
        testUTD.reorderOddEven();
        assertTrue(testUTD.getarrayUTD());

    }

    /**
     * Tests how sizes are affected after simple calls. Will use size as a tester throughout
     */
    @org.junit.Test
    public void size() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        assertEquals(1, testInteger.size());
        testInteger.add(10);
        testInteger.add(10);
        assertEquals(3, testInteger.size());
    }

    /**
     * Tests when the adaptive list is empty. Will use isEmpty during other cases
     */
    @org.junit.Test
    public void isEmpty() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        assertTrue(testInteger.isEmpty());
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        assertFalse(testInteger.isEmpty());
        testInteger.add(14);
        testInteger.add(16);
    }

    /**
     * Tests that add will append new items to end of list
     */
    @Test
    public void add() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 12, 14, 16}, temp1);
        // System.out.println(testInteger.toStringLinked());

//        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
//        testInteger.add(9);
//        testInteger.add(10);
//        testInteger.add(12);
//        testInteger.add(14);
//        testInteger.add(16);
//
//        assertArrayEquals(new Object[]{9,10,12,14,16}, testInteger.toArray());
    }

    /**
     * Tests addAll works with simple arrayList calls
     */
    @org.junit.Test
    public void addAll() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        ArrayList<Integer> testIntegerCollection = new ArrayList<Integer>();
        testIntegerCollection.add(9);
        testIntegerCollection.add(19);
        testIntegerCollection.add(119);
        testIntegerCollection.add(1119);
        testIntegerCollection.add(11119);

        testInteger.addAll(testIntegerCollection);

        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 19, 119, 1119, 11119}, temp1);

        System.out.println(testInteger.toStringLinked());

        testIntegerCollection.add(91);
        testIntegerCollection.add(1911);
        testIntegerCollection.add(119111);
        testIntegerCollection.add(11191111);
        testIntegerCollection.add(1111911111);
        testInteger.addAll(testIntegerCollection);
        System.out.println(testInteger.toStringLinked());
        Object[] temp2 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 19, 119, 1119, 11119, 9, 19, 119, 1119, 11119, 91, 1911, 119111, 11191111,
                1111911111}, temp2);
    }

    /**
     * Tests adaptiveLists remove with different cases
     */
    @org.junit.Test
    public void remove() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.remove((Integer) 10);
        assertFalse(testInteger.getarrayUTD());
        assertTrue(testInteger.getlinkedUTD());
        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 12, 14, 16}, temp1);

        testInteger.add(15);
        testInteger.remove((Integer) 9);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{12, 14, 16, 15}, temp1);
//        System.out.println(testInteger.toStringLinked());
    }

    /**
     * Tests the AdaptiveList add method that takes position as a parameter
     */
    @org.junit.Test
    public void add1() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        //System.out.println(testInteger.toStringLinked());
        testInteger.add(5, 5);
        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 12, 14, 16, 5}, temp1);

        testInteger.add(2, 91);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 91, 12, 14, 16, 5}, temp1);
        // System.out.println(testInteger.toStringLinked());
        // testInteger.remove(1);
        // System.out.println(testInteger.toStringLinked());
        //testInteger.add(15);
        //  testInteger.remove(3);
        //System.out.println(testInteger.toStringLinked());
        //currently functions as intended. Because list is changed as we go, we remove 10 & 16.
    }

    /**
     *Tests AdaptiveLists addAll method that takes position as a parameter
     */
    @org.junit.Test
    public void addAll1() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        ArrayList<Integer> testIntegerCollection = new ArrayList<Integer>();
        testIntegerCollection.add(9);
        testIntegerCollection.add(19);
        testIntegerCollection.add(119);
        testIntegerCollection.add(1119);
        testIntegerCollection.add(11119);

        testInteger.addAll(testIntegerCollection);

        testIntegerCollection.add(91);
        testIntegerCollection.add(1911);
        testIntegerCollection.add(119111);
        testIntegerCollection.add(11191111);
        testIntegerCollection.add(1111911111);
        testInteger.addAll(2, testIntegerCollection);

        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 19, 9, 19, 119, 1119, 11119, 91, 1911, 119111, 11191111, 1111911111, 119,
                1119, 11119}, temp1);
        testInteger.addAll(testInteger);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{
                9, 19, 9, 19, 119, 1119, 11119, 91, 1911, 119111, 11191111, 1111911111, 119, 1119, 11119, 9, 19, 9, 19,
                119, 1119, 11119, 91, 1911, 119111, 11191111, 1111911111, 119, 1119, 11119}, temp1);
    }

    /**
     * Tests AdaptiveLists remove that takes position as a parameter
     */
    @org.junit.Test
    public void remove1() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        testInteger.remove(1);
        assertArrayEquals(new Object[]{9,12,14,16}, testInteger.toArray());
        testInteger.remove(0);
        assertArrayEquals(new Object[]{12,14,16}, testInteger.toArray());
        testInteger.remove(2);
        assertArrayEquals(new Object[]{12,14}, testInteger.toArray());
    }

    /**
     * Tests AdaptiveLists get method that retrieves an E at a specified position
     */
    @org.junit.Test
    public void get() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        assertEquals((Integer) 9, testInteger.get(0));
        assertEquals((Integer) 10, testInteger.get(1));
        assertEquals((Integer) 16, testInteger.get(4));
    }

    /**
     * Tests AdaptiveLIsts set method that changes the data of an array location
     */
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void set() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);


        testInteger.set(0, 5);
        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{5, 10, 12, 14, 16}, temp1);

        testInteger.set(1, 12);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{5, 12, 12, 14, 16}, temp1);

        testInteger.set(1, 15);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{5, 15, 12, 14, 16}, temp1);

        testInteger.set(4, 99);
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{5, 15, 12, 14, 99}, temp1);

        testInteger.set(12, 0);

    }

    /**
     * Tests AdaptiveLists reverse method that reverses the array
     */
    @org.junit.Test
    public void reverse() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        testInteger.reverse();
        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{16, 14, 12, 10, 9}, temp1);


        testInteger.reverse();
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 12, 14, 16}, temp1);

        testInteger.add(99);
        testInteger.reverse();

        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{99, 16, 14, 12, 10, 9}, temp1);

        testInteger.reverse();
        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 12, 14, 16, 99}, temp1);

    }

    /**
     * tests AdaptiveLists reorderOddEven method that swaps odds with evens
     */
    @org.junit.Test
    public void reorderOddEven() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.add(9);
        testInteger.add(9);
        testInteger.add(14);
        testInteger.add(12);

        testInteger.reorderOddEven();
        Object[] temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{10, 9, 14, 12, 9, 16, 14, 9, 12}, temp1);

        testInteger.reorderOddEven();

        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{9, 10, 12, 14, 16, 9, 9, 14, 12}, temp1);

        testInteger.add(31);
        testInteger.reorderOddEven();

        temp1 = testInteger.toArray();
        assertArrayEquals(new Object[]{10, 9, 14, 12, 9, 16, 14, 9, 31, 12}, temp1);
    }

    //Tests that the specified element is contained within the AdaptiveList
    @org.junit.Test
    public void contains() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        assertTrue(testInteger.contains(16));
        assertTrue(testInteger.contains(9));
        assertTrue(testInteger.contains(10));
        assertFalse(testInteger.contains(100));
//        Object[] temp1 = testInteger.toArray();
//        // TODO: 10/22/2018
//        System.out.println(testInteger.toStringLinked());
//        System.out.println(testInteger.contains((Integer) 9));
//        System.out.println(testInteger.contains((Integer) 10));
//        System.out.println(testInteger.contains((Integer) 16));
//        System.out.println(testInteger.contains((Integer) 99));
//        System.out.println(testInteger.contains((Integer) 1));
    }

    //Tests that the AdaptiveLists contains all specified elements in a collection
    @org.junit.Test
    public void containsAll() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        ArrayList<Integer> x = new ArrayList<Integer>();
        x.add(9);
        x.add(14);
        x.add(16);
        x.add(12);
        x.add(16);

        assertTrue(testInteger.containsAll(x));
        x.add(7);
        assertFalse(testInteger.containsAll(x));
       // System.out.println(testInteger.containsAll(x));
//        AdaptiveList<Integer> testInteger1 = new AdaptiveList<Integer>();
//        testInteger1 = testInteger;
//        System.out.println(testInteger.containsAll(testInteger1) + ": Expected true");
    }

    /**
     * Tests the indexOf method from AdaptiveList that finds the Index of an element
     */
    @org.junit.Test
    public void indexOf() {
        // TODO: 10/23/2018
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        assertEquals(0, testInteger.indexOf(9));
        assertEquals(4, testInteger.indexOf(16));
        assertEquals(-1, testInteger.indexOf(100));
//
//        System.out.println(testInteger.indexOf((Integer) 10));
//        System.out.println(testInteger.indexOf((Integer) 9));
//        System.out.println(testInteger.indexOf((Integer) 16));
//        System.out.println(testInteger.indexOf((Integer) 99));
    }

    /**
     * Tests AdaptiveLists elements to find last index of specified object
     */
    @org.junit.Test
    public void lastIndexOf() {
        // TODO: 10/23/2018
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.add(9);
        testInteger.add(9);
        testInteger.add(14);
        testInteger.add(12);

        assertEquals(8, testInteger.lastIndexOf(12));
        assertEquals(6, testInteger.lastIndexOf(9));
        assertEquals(-1, testInteger.lastIndexOf(10000));

    }

    /**
     * Tests AdaptiveLists removeAll function that removes all cases of objects from a collection
     */
    @org.junit.Test
    public void removeAll() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.add(9);
        testInteger.add(9);
        testInteger.add(14);
        testInteger.add(12);

        ArrayList<Integer> x = new ArrayList<>();
        x.add(12);

        testInteger.removeAll(x);
        assertFalse(testInteger.contains(12));

        x.add(16);
        testInteger.removeAll(x);
        assertFalse(testInteger.contains(16));

    }

    /**
     * Tests adaptiveLists retainAll function that retains specified objects in a collection
     */
    @org.junit.Test
    public void retainAll() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.add(9);
        testInteger.add(9);
        testInteger.add(14);
        testInteger.add(12);
        testInteger.add(10);

        ArrayList<Integer> testInteger1 = new ArrayList<Integer>();
        testInteger1.add(10);

        testInteger.retainAll(testInteger1);
        assertArrayEquals(new Object[]{10, 10}, testInteger.toArray());

        testInteger1.remove(0);
        testInteger.retainAll(testInteger1);
        assertTrue(testInteger.isEmpty());

    }

    /**
     * Tests the functionality of AdaptiveLists toArray method that returns a new array representaion of the list
     */
    @org.junit.Test
    public void toArray() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        testInteger.add(9);
        testInteger.add(9);
        testInteger.add(14);
        testInteger.add(12);
        testInteger.add(10);

        assertArrayEquals(new Object[]{9,10,12,14,16,9,9,14,12,10}, testInteger.toArray());
    }

    /**
     * Tests AdaptiveLists toArray method that takes an array as a parameter
     */
    @org.junit.Test
    public void toArray1() {
        AdaptiveList<Object> testObject = new AdaptiveList<>();
        testObject.add((Integer) 10);
        testObject.add((Integer) 15);
        Integer[] testIntegerArr = new Integer[3];
        System.out.println(testIntegerArr.getClass());

        assertEquals(testIntegerArr.getClass(), testObject.toArray(testIntegerArr).getClass());
        testIntegerArr = testObject.toArray(testIntegerArr);
        assertEquals((Integer) 10, testIntegerArr[0]);
        assertEquals((Integer) 15, testIntegerArr[1]);
        assertEquals(null, testIntegerArr[2]);
    }

    /**
     * Tests the functionality of the iterator from AdaptiveList
     */
    @org.junit.Test
    public void iterator() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        Iterator x = testInteger.iterator();
    }

    @org.junit.Test
    public void listIterator() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        ListIterator<Integer> y = testInteger.listIterator();
    }

    @org.junit.Test
    public void listIterator1() {
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);
        ListIterator<Integer> x = testInteger.listIterator(1);
        assertEquals((Integer) 10, x.next());
    }

    /**
     * Tests the functionality of hasNext from AdaptiveLitIterator
     */
    @Test
    public void hasNextIterator(){

        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator();
        assertTrue(x.hasNext());
        x.next();
        x.next();
        x.next();
        x.next();
        assertTrue(x.hasNext());
        x.next();
        assertFalse(x.hasNext());

    }

    /**
     * Tests the functionality of next from AdaptiveListIterator
     */
    @Test
    public void nextIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator();
        assertEquals((Integer) 9, x.next());
        assertEquals((Integer) 10, x.next());
        assertEquals((Integer) 12, x.next());
        assertEquals((Integer) 14, x.next());
        assertEquals((Integer) 16, x.next());

    }

    /**
     * Test the functionality of hasPrevious from AdaptliveListIterator
     */
    @Test
    public void hasPreviousIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator();
        assertFalse(x.hasPrevious());
        x.next();
        assertTrue(x.hasPrevious());
        x.next();
        x.next();
        x.next();
        x.next();
        assertTrue(x.hasPrevious());
    }

    /**
     * Tests the functionality of previos from AdaptiveListsIterator
     */
    @Test
    public void previousIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator(5);
        assertEquals((Integer) 16, x.previous());
        assertEquals((Integer) 14, x.previous());
        assertEquals((Integer) 12, x.previous());
        assertEquals((Integer) 10, x.previous());
        assertEquals((Integer) 9, x.previous());
    }

    /**
     * Tests the functionality of NextIndex from AdaptiveListIterator
     */
    @Test
    public void nextIndexIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator();
        assertEquals(0, x.nextIndex());
        x.next();
        assertEquals(1, x.nextIndex());
        x.next();
        x.next();
        x.next();
        x.next();
        assertEquals(5, x.nextIndex());

    }

    /**
     * Tests the functionality of previousIndex from AdaptiveListIterator
     */
    @Test
    public void previousIndexIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator(5);
        assertEquals(4, x.previousIndex());
        x.previous();
        assertEquals(3, x.previousIndex());
        x.previous();
        x.previous();
        x.previous();
        x.previous();
        assertEquals(-1, x.previousIndex());
    }

    /**
     * Tests the functionality of remove from AdaptiveListIterator
     */
    @Test
    public void removeIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(9);
        testInteger.add(10);
        testInteger.add(12);
        testInteger.add(14);
        testInteger.add(16);

        ListIterator<Integer> x = testInteger.listIterator();
        x.next();
        x.remove();
        assertEquals(-1, x.previousIndex());
    }

    /**
     * Tests the functionality of add from AdaptiveListIterator
     */
    @Test
    public void addIterator(){
        AdaptiveList<Integer> testInteger = new AdaptiveList<Integer>();
        testInteger.add(10);
        ListIterator<Integer> x = testInteger.listIterator();
        x.add(100);
        x.add(99);
        assertEquals((Integer) 10, x.next());
        x.previous();
        assertEquals((Integer) 99, x.previous());
        assertEquals((Integer) 100, x.previous());
    }
}