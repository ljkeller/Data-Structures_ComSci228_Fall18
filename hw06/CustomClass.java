package edu.iastate.cs228.hw06;



/**
 * 
 * @author Lucas Keller
 * 
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods. 
 * 2. No additional data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 *    (Except for the methods that are provided already for you, which
 *    do not need to be implemented as part of this HW, i.e. needs to be
 *    used as it is.)
 * 6. You are allowed to reuse any part of the source codes of provided
 *    or shown under lecture notes.
 *    
 * All above 0 - 6 apply also to AList, LList, and LListWithTail.
 * 
 * 
 * 
 * REQUIREMENTS:
 *    All methods in this class need to be overridden in all of AList, 
 *    LList, and LListWithTail classes as per contract. You are allowed 
 *    to reuse already implemented methods in there while implementing
 *    these.
 * 
 * HINT:
 *    You are allowed to provide an implementation, i.e., body, of these
 *    methods here. In that case remove the abstract keyword. Otherwise,
 *    if you are providing implementation in specific classes then you 
 *    need to add @author tag in those classes with your name, and keep
 *    the abstract keyword in the signature of the methods below. In the
 *    former case, i.e., when you remove the abstract keywords here and
 *    provide the implementations here, no need to provided @author tag
 *    in classes AList, LList, and LListWithTail.
 *  
 */


public abstract class CustomClass<T> implements ListInterface<T>
{
	/**
	 * Adds a new entry to the beginning of this list.
	 * 
	 * 
	 */
	public abstract void addFirst(T newEntry);
	
	
	
	/**
	 * Adds a new entry to the end of this list.
	 * 
	 * 
	 */
	public abstract void addLast(T newEntry);
	
	/**
	 * Removes and returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public abstract T removeFirst();
	
	
	/**
	 * Removes and returns the last entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public abstract T removeLast();
	
	
	/**
	 * Returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public abstract T getFirst();
	
	/**
	 * Returns the last entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public abstract T getLast();
	
	
	/**
	 * Moves the first entry in this list to the end 
	 * of the list. If only single entry or
	 * the list is empty then does nothing.
	 * 
	 */
	public abstract void moveToEnd();
	
	/**
	 * Removes the first occurrence of anEntry from
	 * the list. Returns true if succefully removes,
	 * otherwise, returns false.
	 * 
	 */
	public abstract boolean remove(T anEntry);
	
	
	/**
	 * Returns the position of the first occurrence of an 
	 * anEntry, if it exists in the list. If not returns -1. 
	 * 
	 */
	public abstract int getPosition(T anEntry);
	
	
}
