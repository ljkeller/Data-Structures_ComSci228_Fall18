package edu.iastate.cs228.hw01;

public class TestMyString {
	public static void main(String[] args) {
		MyString s = new MyString(new char[] { 'a', 'b', 'c' });
		System.out.println(s.length());
		System.out.println(s.charAt(1));
		System.out.println(s.equals(new MyString(new char[] { 'a', 'b', 'c' })));
		char[] chars = MyString.valueOf(345).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}
	}
}/* Outputs:3bfalse345 */