package edu.iastate.cs228.proj3;

/*
 *  @author Lucas Keller
 *
 *
 *  An implementation of List<E> based on a doubly-linked list
 *  with an array for indexed reads/writes
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E> {
	public class ListNode {
		public E data;
		public ListNode next;
		public ListNode prev;

		public ListNode(E item) {
			data = item;
			next = prev = null;
		}
	}

	public ListNode head; // dummy node made public for testing.
	public ListNode tail; // dummy node made public for testing.
	private int numItems; // number of data items
	private boolean linkedUTD; // true if the linked list is up-to-date.

	public E[] theArray; // the array for storing elements
	private boolean arrayUTD; // true if the array is up-to-date.

	public AdaptiveList() {
		clear();
	}

	@Override
	public void clear() {
		head = new ListNode(null);
		tail = new ListNode(null);
		head.next = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	public boolean getlinkedUTD() {
		return linkedUTD;
	}

	public boolean getarrayUTD() {
		return arrayUTD;
	}

	public AdaptiveList(Collection<? extends E> c) {
		clear();
		addAll(c);
	}

	// Removes the node from the linked list.
	// This method should be used to remove a node
	// from the linked list.
	private void unlink(ListNode toRemove) {
		if (toRemove == head || toRemove == tail)
			throw new RuntimeException("An attempt to remove head or tail");
		toRemove.prev.next = toRemove.next;
		toRemove.next.prev = toRemove.prev;
	}

	// Inserts new node toAdd right after old node current.
	// This method should be used to add a node to the linked list.
	private void link(ListNode current, ListNode toAdd) {
		if (current == tail)
			throw new RuntimeException("An attempt to chain after tail");
		if (toAdd == head || toAdd == tail)
			throw new RuntimeException("An attempt to add head/tail as a new node");
		toAdd.next = current.next;
		toAdd.next.prev = toAdd;
		toAdd.prev = current;
		current.next = toAdd;
	}

	private void updateArray() // makes theArray up-to-date.
	{
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!linkedUTD)
			throw new RuntimeException("linkedUTD is false");

		theArray = (E[]) new Object[numItems];
		ListNode cur = head.next;
		for (int i = 0; i < numItems; i++) {
			theArray[i] = cur.data;
			cur = cur.next;
		}

		arrayUTD = true;
	}

	private void updateLinked() // makes the linked list up-to-date.
	{
		if (numItems < 0)
			throw new RuntimeException("numItems is negative: " + numItems);
		if (!arrayUTD)
			throw new RuntimeException("arrayUTD is false");

		if (theArray == null || theArray.length < numItems)
			throw new RuntimeException("theArray is null or shorter");

		head.next = tail; // reset head and tail to empty
		tail.prev = head;

		for (int i = numItems - 1; i >= 0; i--) // start at end, using link to head
			link(head, new ListNode(theArray[i]));
		linkedUTD = true;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public boolean add(E obj) {
		if (!linkedUTD)
			updateLinked();
		ListNode newNode = new ListNode(obj);
		link(tail.prev, newNode);

		numItems++;
		arrayUTD = false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {/*
														 * using addAll(pos,collection) at pos = size - 1 will be slower
														 * due to findNode, so we write repetitive code
														 */
		if (c.isEmpty())
			return false;

		if (!linkedUTD)
			updateLinked();

		int fixedSize = c.size();
		Iterator<? extends E> throughCollection = c.iterator();
		for (int i = 0; i < fixedSize; i++) {
			link(tail.prev, new ListNode(throughCollection.next()));
			numItems++;
		}
		arrayUTD = false;
		return true;
		// cant use lambda cause infinity loop possibility :(
		// throughCollection.forEachRemaining(action -> {
		// link(tail.prev, new ListNode(throughCollection.next()));
		// numItems++;
		// }
		// );
	}

	@Override
	public boolean remove(Object obj) {
		if (!linkedUTD)
			updateLinked();
		for (ListNode cur = head.next; cur != tail; cur = cur.next) {
			if (cur.data.equals(obj)) {
				unlink(cur);
				numItems--;
				arrayUTD = false;
				return true;
			}
		}

		return false; // if made this far, didn't find item
	}

	private void checkIndex(int pos) // a helper method
	{
		if (pos >= numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkIndex2(int pos) // a helper method
	{
		if (pos > numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkNode(ListNode cur) // a helper method
	{
		if (cur == null || cur == tail)
			throw new RuntimeException("numItems: " + numItems + " is too large");
	}

	private ListNode findNode(int pos) // a helper method
	{
		ListNode cur = head;
		for (int i = 0; i < pos; i++) {
			checkNode(cur);
			cur = cur.next;
		}
		checkNode(cur);
		return cur;
	}

	@Override
	public void add(int pos, E obj) {
		checkIndex2(pos);// checks for bounds
		if (!linkedUTD)
			updateLinked();
		link(findNode(pos), new ListNode((obj))); // adds object using link
		numItems++;
		arrayUTD = false;
	}

	@Override
	public boolean addAll(int pos, Collection<? extends E> c) {
		checkIndex2(pos);
		if (c.isEmpty())
			return false;
		if (!linkedUTD)
			updateLinked();
		int fixedSize = c.size();
		ListNode temp = findNode(pos).next;// Node one before where we add

		Iterator<? extends E> throughCollection = c.iterator();
		for (int i = 0; i < fixedSize; i++) {
			link(temp.prev, new ListNode(throughCollection.next())); // adds in order they give by using temp.next
			numItems++;
		}
		arrayUTD = false;
		return true;
	}

	@Override
	public E remove(int pos) {
		checkIndex(pos);

		if (!linkedUTD)
			updateLinked();
		E temp = findNode(pos).next.data;
		unlink(findNode(pos).next);

		numItems--;
		arrayUTD = false;

		return temp;
	}

	@Override
	public E get(int pos) {
		checkIndex2(pos);
		if (!arrayUTD)
			updateArray();
		return theArray[pos];
	}

	@Override
	public E set(int pos, E obj) {
		checkIndex2(pos);
		if (!arrayUTD)
			updateArray();

		E temp = theArray[pos];
		theArray[pos] = obj;
		linkedUTD = false;
		return temp;
	}

	/**
	 * If the number of elements is at most 1, the method returns false. Otherwise,
	 * it reverses the order of the elements in the array without using any
	 * additional array, and returns true. Note that if the array is modified, then
	 * linkedUTD needs to be set to false.
	 */
	public boolean reverse() {
		if (size() <= 1)
			return false;
		if (!arrayUTD)
			updateArray();

		E tempRight;
		for (int i = 0; i < numItems / 2; i++) {
			tempRight = theArray[numItems - (i + 1)];
			theArray[numItems - (i + 1)] = theArray[i];
			theArray[i] = tempRight;
		}
		linkedUTD = false;
		return true;
	}

	/**
	 * If the number of elements is at most 1, the method returns false. Otherwise,
	 * it swaps the items positioned at even index with the subsequent one in odd
	 * index without using any additional array, and returns true. Note that if the
	 * array is modified, then linkedUTD needs to be set to false.
	 */
	public boolean reorderOddEven() {
		if (size() <= 1)
			return false;
		if (!arrayUTD)
			updateArray();

		E tempOdd; // the odd number in array to be swapped
		for (int i = 0; i < size() - 2; i += 2) { // goes until atleast 2 left
			tempOdd = theArray[i + 1];
			theArray[i + 1] = theArray[i];
			theArray[i] = tempOdd;
		}
		if (size() % 2 == 0) { // if 2 remain unswapped, swap them
			tempOdd = theArray[size() - 1];
			theArray[size() - 1] = theArray[size() - 2];
			theArray[size() - 2] = tempOdd;
		}
		linkedUTD = false;

		return true;
	}

	@Override
	public boolean contains(Object obj) {
		if (!linkedUTD)
			updateLinked();
		for (ListNode cur = head.next; cur != tail; cur = cur.next) {
			if (cur.data.equals(obj))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (!linkedUTD)
			updateLinked();
		if (this == c) // checks if collection is this collection
			return true;
		Iterator<?> tempIterator = c.iterator();
		while (tempIterator.hasNext())
			if (!this.contains(tempIterator.next()))
				return false;
		return true;
	}

	@Override
	public int indexOf(Object obj) {
		if (!linkedUTD)
			updateLinked();
		int counter = 0;
		for (ListNode cur = head.next; cur != tail; cur = cur.next) {
			if (cur.data.equals(obj))
				return counter;
			else
				counter++;
		}
		return -1; // returns 1 if not found
	}

	@Override
	public int lastIndexOf(Object obj) {
		if (!linkedUTD)
			updateLinked();
		int counter = size() - 1;
		for (ListNode cur = tail.prev; cur != head; cur = cur.prev) {// iterate back starting from tail.prev
			if (cur.data.equals(obj))
				return counter;
			else
				counter--;
		}
		return -1; // return -1 if not found
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> temp = c.iterator();
		boolean hasChanged = false;
		Object contents;
		if (!temp.hasNext())// if colllection is empty, returns false and doesn't update anything
			return hasChanged;

		if (!linkedUTD)
			updateLinked();

		while (temp.hasNext()) {
			contents = temp.next();
			while (this.contains(contents)) {
				remove(contents);
				hasChanged = true;
			}
		}

		return hasChanged; // Dont update arrayUTD because remove does that for us
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean hasChanged = false;

		if (!linkedUTD)
			updateLinked();

		for (ListNode cur = head.next; cur != tail; cur = cur.next) {
			if (!c.contains(cur.data)) {
				cur = cur.prev;
				remove(cur.next.data);
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	@Override
	public Object[] toArray() {
		if (!linkedUTD)
			updateLinked();
		Object[] tempArray = new Object[numItems];
		ListNode cur = head.next;
		for (int i = 0; i < numItems; i++) {
			tempArray[i] = cur.data;
			cur = cur.next;
		}
		return tempArray;
	}

	/**
	 * In here you are allowed to use only java.util.Arrays.copyOf method.
	 */
	@Override
	public <T> T[] toArray(T[] arr) {

		if (!linkedUTD)
			updateLinked();

		if (arr.length < numItems)
			arr = java.util.Arrays.copyOf(arr, numItems);
		ListNode cur = head.next;
		for (int i = 0; i < numItems; i++) {
			arr[i] = (T) cur.data;
			cur = cur.next;
		}

		if (arr.length > numItems)
			arr[numItems] = null;
		return arr;
	}

	@Override
	public List<E> subList(int fromPos, int toPos) {
		throw new UnsupportedOperationException();
	}

	private class AdaptiveListIterator implements ListIterator<E> {
		private int index; // index of next node;
		private ListNode cur; // node at index - 1
		private ListNode last; // node last visited by next() or previous()

		public AdaptiveListIterator() {
			if (!linkedUTD)
				updateLinked();
			index = 0;
			cur = head;
			last = null;
		}

		public AdaptiveListIterator(int pos) {
			checkIndex2(pos);
			if (!linkedUTD)
				updateLinked();
			index = pos;
			last = null;
			cur = findNode(pos);
		}

		@Override
		public boolean hasNext() {
			return index < numItems;
		}

		@Override
		public E next() {
			if (!linkedUTD)
				updateLinked();
			if (!hasNext())
				throw new NoSuchElementException();
			cur = cur.next;
			last = cur; // cur == next
			index++;
			return cur.data;
		}

		@Override
		public boolean hasPrevious() {
			return index > 0 && index <= size();
		}

		@Override
		public E previous() {
			if (!linkedUTD)
				updateLinked();
			if (!hasPrevious())
				throw new NoSuchElementException();
			last = cur;
			cur = cur.prev;
			index--;
			return last.data;
		}

		@Override
		public int nextIndex() {
			return (hasNext()) ? index : numItems;
		}

		@Override
		public int previousIndex() {
			return (hasPrevious()) ? index - 1 : -1;
		}

		@Override
		public void remove() {
			if (!linkedUTD)
				updateLinked();
			if (last == null)
				throw new IllegalStateException();
			if (last == cur)
				index--;
			cur = cur.prev;
			unlink(last);
			numItems--;
			arrayUTD = false;
			last = null;
		}

		@Override
		public void add(E obj) {
			if (!linkedUTD)
				updateLinked();
			ListNode tempNode = new ListNode(obj);
			link(cur, tempNode);
			cur = cur.next;
			numItems++;
			index++;
			arrayUTD = false;
			last = null;
		}

		@Override
		public void set(E obj) {
			if (!linkedUTD)
				updateLinked();
			if (last == null)
				throw new IllegalStateException();
			last.data = obj;
			arrayUTD = false;
			last = null;
		}
	} // AdaptiveListIterator

	@Override
	public boolean equals(Object obj) {
		if (!linkedUTD)
			updateLinked();
		if ((obj == null) || !(obj instanceof List<?>))
			return false;
		List<?> list = (List<?>) obj;
		if (list.size() != numItems)
			return false;
		Iterator<?> iter = list.iterator();
		for (ListNode tmp = head.next; tmp != tail; tmp = tmp.next) {
			if (!iter.hasNext())
				return false;
			Object t = iter.next();
			if (!(t == tmp.data || t != null && t.equals(tmp.data)))
				return false;
		}
		if (iter.hasNext())
			return false;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int pos) {
		checkIndex2(pos);
		return new AdaptiveListIterator(pos);
	}

	// Adopted from the List<E> interface.
	@Override
	public int hashCode() {
		if (!linkedUTD)
			updateLinked();
		int hashCode = 1;
		for (E e : this)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	// You should use the toString*() methods to see if your code works as expected.
	@Override
	public String toString() {
		// Other options System.lineSeparator or
		// String.format with %n token...
		// Not making data field.
		String eol = System.getProperty("line.separator");
		return toStringArray() + eol + toStringLinked();
	}

	public String toStringArray() {
		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent array:" + eol);
		strb.append('[');
		if (theArray != null)
			for (int j = 0; j < theArray.length;) {
				if (theArray[j] != null)
					strb.append(theArray[j].toString());
				else
					strb.append("-");
				j++;
				if (j < theArray.length)
					strb.append(", ");
			}
		strb.append(']');
		return strb.toString();
	}

	public String toStringLinked() {
		return toStringLinked(null);
	}

	// iter can be null.
	public String toStringLinked(ListIterator<E> iter) {
		int cnt = 0;
		int loc = iter == null ? -1 : iter.nextIndex();

		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent linked list:" + eol);
		strb.append('(');
		for (ListNode cur = head.next; cur != tail;) {
			if (cur.data != null) {
				if (loc == cnt) {
					strb.append("| ");
					loc = -1;
				}
				strb.append(cur.data.toString());
				cnt++;

				if (loc == numItems && cnt == numItems) {
					strb.append(" |");
					loc = -1;
				}
			} else
				strb.append("-");

			cur = cur.next;
			if (cur != tail)
				strb.append(", ");
		}
		strb.append(')');
		return strb.toString();
	}
}