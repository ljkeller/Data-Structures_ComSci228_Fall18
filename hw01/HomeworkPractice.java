package edu.iastate.cs228.hw01;

import java.awt.Color;

public class HomeworkPractice {
	public static void main(String args[]) {
		Triangle test = new Triangle(5, 5, 5);
		
		System.out.println(test.getArea());
		System.out.println(test.getPerimeter());
		System.out.println(test.toString());
		
		System.out.println(String.valueOf(15));

		char[] megan = "mMgan".toCharArray();
		char[] test1 = "aBcDeFgHiJkL".toCharArray();
		char[] test2 = "mMgan".toCharArray();
		System.out.println(megan);
		MyString tester = new MyString(megan);
		MyString Tester1 = new MyString(test1);
//		System.out.println(tester.subString(2,3).toCharArray());
		System.out.println(tester.length());
		System.out.println(Tester1.toLowerCase().toCharArray());
//		System.out.println("My name is Lucas".substring(5, 3));
		char[] test4 = "154652652".toCharArray();
		MyString test3 = new MyString(test4);
		System.out.println(test3.toCharArray());
		
		char[] test11 = "HELLO W".toCharArray();
		MyString test00 = new MyString(test11);
		System.out.println(test00.length());
//		test11 = null;
//		System.out.println(test00.toCharArray());
		
	}
}
