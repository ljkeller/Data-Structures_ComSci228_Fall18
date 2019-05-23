package edu.iastate.cs228.proj3;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainTester {
    public static void main(String[] args) {
        ArrayList<Integer> tempTester = new ArrayList<Integer>();
        tempTester.add(10);
        tempTester.add(15);
        tempTester.add(20);
        tempTester.add(25);
        tempTester.add(30);
        tempTester.add(15);
        tempTester.add(15);
        tempTester.add(20);
        tempTester.add(25);


       // ListIterator xx = tempTester.listIterator();
       // System.out.println(xx.nextIndex() + " yo");

        ListIterator xy = tempTester.listIterator(8);
        System.out.println(xy.nextIndex());
        System.out.println(xy.next());
//
//        ArrayList<Integer> tempTester1 = new ArrayList<Integer>();
//        tempTester1.add(20);
//        tempTester1.add(20);
//        tempTester1.add(20);
//        tempTester1.add(20);
//        tempTester1.add(20);
//
//
//        ArrayList<Integer> tempTester2 = new ArrayList<Integer>();
//        tempTester2.add(15);
//
//        System.out.println(tempTester.containsAll(tempTester));
//        System.out.println(tempTester.containsAll(tempTester1));
//        System.out.println(tempTester.containsAll(tempTester2));
//        System.out.println(tempTester.removeAll(tempTester2));
//        System.out.println(tempTester.toString());
//        System.out.println(tempTester1.toString());
//        tempTester1.retainAll(new ArrayList<Integer>());
//        System.out.println(tempTester1.toString());
        ListIterator<Integer> testDumb = tempTester.listIterator();
        System.out.println(testDumb.nextIndex());
        System.out.println(testDumb.previousIndex());
    }
}
