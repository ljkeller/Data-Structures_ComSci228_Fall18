package edu.iastate.cs228.hw05;

/**
 * A class of priority queues represented by a chain of linked nodes.
 * 
 * @author Lucas Keller
 * 
 *         NOTE: 0. Put your Firstname and Lastname after above author tag. Make
 *         sure that in both cases the first letter is uppercase and all others
 *         are lowercase. 1. You are allowed to create and use your own private
 *         helper methods. 2. No additional data fields can be introduced. 3. No
 *         custom classes of your own can be introduced or used. 4. Import
 *         statements are not allowed. 5. Fully qualified class names usage is
 *         not allowed. 6. You are allowed to reuse any part of the source codes
 *         of provided source codes or shown under lecture notes. 7. You are not
 *         allowed to create arrays of objects and manipulate queue objects
 *         using arrays.
 * 
 *
 *         DESCRIPTION: A class of priority queues represented by a linked chain
 *         of nodes.
 * 
 *         For details of priority queue implementation using linked chain of
 *         nodes, check slide number 14 of
 *         "queueDequePriorityQueueImplementations_part3.pdf" file under lecture
 *         notes of Friday of Week 5 on Canvas.
 */

public final class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	private Node firstNode; // Reference to first node of chain and the front
							// of the priority queue, which has the highest priority
	private int length; // Number of entries in chain

	public LinkedPriorityQueue() {
		firstNode = null;
		length = 0;
	}

	public void add(T newEntry) {
		Node beforeNode = getBeforeNode(newEntry);
		if (beforeNode == null && length == 0) { // if empty, place at begginning
			Node newNode = new Node(newEntry, null);
			firstNode = newNode;
			length++;
		} else if (beforeNode == null && length > 0) { // if newEntry is greatest size
			Node newNode = new Node(newEntry, firstNode);
			firstNode = newNode;
			length++;
		} else {
			Node newNode = new Node(newEntry, beforeNode.getNextNode());
			beforeNode.setNextNode(newNode);
			length++;
		}
	}

	public T remove() {
		if (isEmpty())
			return null;
		else {
			T temp = firstNode.data;
			firstNode = firstNode.getNextNode();
			length--;
			return temp;
		}
	}

	public T peek() {
		if (isEmpty())
			return null;
		else {
			return firstNode.getData();
		}
	}

	/**
	 * If queue is empty returns []. Else, returns as [1, 2, 3] Important: note a
	 * comma and single space before every item except the last, and after last
	 * there is no space. In both cases before and after square brackets there is no
	 * space.
	 * 
	 */
	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		else if (length == 1)
			return "[" + firstNode.getData() + "]";
		else {
			StringBuilder temp = new StringBuilder();
			Node iteratorNode = firstNode;
			temp.insert(0, "]");
			for (int i = length; i > 1 && iteratorNode != null; i--) {
				temp.insert(0, ", " + iteratorNode.data);
				iteratorNode = iteratorNode.getNextNode();
			}
			temp.insert(0, "[" + iteratorNode.getData());
			return temp.toString();

		}
	}

	public boolean isEmpty() {
		boolean result;

		if (length == 0) {
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		}

		return result;
	}

	public int getSize() {
		return length;
	}

	public void clear() {
		firstNode = null;
		length = 0;
	}

	/**
	 * Finds node before where anEntry should be placed. If no node is found,
	 * returns null;
	 * 
	 * @param anEntry
	 *            Entry to be placed within linked list
	 * @return Node before where the new node will be placed, based on priority
	 */
	private Node getBeforeNode(T anEntry) {
		Node iteratorNode = firstNode;
		int counter = 0;
		while (counter < length && iteratorNode != null) {
			if (anEntry.compareTo(iteratorNode.getData()) <= 0 && iteratorNode.getNextNode() == null)// if reaching end
																										// of list
				return iteratorNode;
			else if (anEntry.compareTo(iteratorNode.getData()) <= 0
					&& anEntry.compareTo(iteratorNode.getNextNode().getData()) > 0)// must check between nodes for both
																					// conditions
				return iteratorNode;
			iteratorNode = iteratorNode.getNextNode();
			counter++;
		}
		return null;
	}

	private class Node {
		private T data; // Entry in priority queue
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
} // end LinkedPriorityQueue