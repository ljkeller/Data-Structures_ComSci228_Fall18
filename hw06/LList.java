package edu.iastate.cs228.hw06;

/**
 * A class that implements the ADT list by using a chain of linked nodes that
 * has a head reference.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Lucas Keller
 * @version 4.0
 */
public class LList<T> extends CustomClass<T> {
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;

	public LList() {
		initializeDataFields();
	} // end default constructor

	public void clear() {
		initializeDataFields();
	} // end clear

	public void add(T newEntry) // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else // Add to end of non-empty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if

		numberOfEntries++;
	} // end add

	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);

			if (newPosition == 1) // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else // Case 2: list is not empty
			{ // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if

			numberOfEntries++;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public T remove(int givenPosition) {
		T result = null; // Return value

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();

			if (givenPosition == 1) // Case 1: Remove first entry
			{
				result = firstNode.getData(); // Save entry to be removed
				firstNode = firstNode.getNextNode(); // Remove entry
			} else // Case 2: Not first entry
			{
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData(); // Save entry to be removed
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter); // Remove entry
			} // end if

			numberOfEntries--; // Update count
			return result; // Return removed entry
		} else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		} // end while

		return result;
	} // end toArray

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while

		return found;
	} // end contains

	public int getLength() {
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty() {
		boolean result;

		if (numberOfEntries == 0) // Or getLength() == 0
		{
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		} // end if

		return result;
	} // end isEmpty

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	// Returns a reference to the node at a given position.
	// Precondition: The chain is not empty;
	// 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition) {
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		assert currentNode != null;

		return currentNode;
	} // end getNodeAt

	private class Node {
		private T data; // Entry in list
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor

		private T getData() {
			return data;
		} // end getData

		private void setData(T newData) {
			data = newData;
		} // end setData

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node


	/**
	 * Adds a new entry to the beginning of this list.
	 * 
	 * @param newEntry
	 * 			new Entry to be added to beginning
	 */
	@Override
	public void addFirst(T newEntry) {
		Node newNode = new Node(newEntry, firstNode);
		numberOfEntries++;
		firstNode = newNode;
	}

	/**
	 * Adds a new entry to the end of this list.
	 * 
	 * @param newEntry
	 * 			new Entry to be added to end
	 */
	@Override
	public void addLast(T newEntry) {
		if (numberOfEntries == 0) {
			addFirst(newEntry);
			return;
		}
		for (Node cur = firstNode; cur != null; cur = cur.getNextNode()) {
			if (cur.getNextNode() == null) {
				cur.setNextNode(new Node(newEntry, null));
				break;
			}
		}
		numberOfEntries++;
	}

	/**
	 * Removes and returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * @return 
	 * 		The first T variable in list
	 */
	@Override
	public T removeFirst() {
		if (numberOfEntries >= 1) {
			T temp = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			return temp;
		} else
			throw new java.util.NoSuchElementException();
	}

	/**
	 * Removes and returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * @return 
	 * 		The final T variable in list
	 */
	@Override
	public T removeLast() {
		if (numberOfEntries == 0)
			throw new java.util.NoSuchElementException();
		else if (numberOfEntries == 1) {
			return removeFirst();
		} else {
			T temp = null;
			for (Node cur = firstNode; cur != null && cur.getNextNode() != null; cur = cur.getNextNode()) {
				if (cur.getNextNode().getNextNode() == null) {
					temp = cur.getNextNode().getData();
					cur.setNextNode(null);
					numberOfEntries--;
					break;
				}
			}
			return temp;
		}

	}

	/**
	 * Returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * @return 
	 * 		The first T variable in list
	 */
	@Override
	public T getFirst() {
		if (numberOfEntries >= 1)
			return firstNode.getData();
		else
			throw new java.util.NoSuchElementException();
	}

	/**
	 * Returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * @return 
	 * 		The last T variable in list
	 */
	@Override
	public T getLast() {
		if (numberOfEntries >= 1) {
			T temp = null;
			for (Node cur = firstNode; cur != null; cur = cur.getNextNode()) {
				if (cur.getNextNode() == null)
					temp = cur.getData();
			}
			return temp;
		} else
			throw new java.util.NoSuchElementException();
	}

	/**
	 * Moves the first entry in this list to the end 
	 * of the list. If only single entry or
	 * the list is empty then does nothing.
	 * 
	 */
	@Override
	public void moveToEnd() {
		if (numberOfEntries > 1) {
			for (Node cur = firstNode; cur != null; cur = cur.getNextNode()) {
				if (cur.getNextNode() == null) {
					cur.setNextNode(firstNode);
					firstNode = firstNode.getNextNode();
					cur.getNextNode().setNextNode(null);
					break;
				}
			}
		}

	}

	/**
	 * Removes the first occurrence of anEntry from
	 * the list. Returns true if succefully removes,
	 * otherwise, returns false.
	 * 
	 */
	@Override
	public boolean remove(T anEntry) {
		if (numberOfEntries >= 1) {
			int nodeBeforeIndex = getPosition(anEntry) - 1;
			if (nodeBeforeIndex == 0) {
				removeFirst();
				return true;
			} else {
				for (Node cur = firstNode; cur != null && cur.getNextNode() != null; cur = cur.getNextNode()) {
					if (cur.getNextNode().getData().equals(anEntry)) {
						cur.setNextNode(cur.getNextNode().getNextNode());
						numberOfEntries--;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int getPosition(T anEntry) {
		if (numberOfEntries >= 1) {
			int counter = 1;
			for (Node cur = firstNode; cur != null; cur = cur.getNextNode()) {
				if (cur.data.equals(anEntry))
					return counter;
				counter++;
			}
		}
		return -1;
	}

} // end LList
