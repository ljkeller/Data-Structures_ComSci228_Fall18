package edu.iastate.cs228.proj4;

import java.security.Key;
import java.sql.SQLOutput;

/**
 * @author Lucas Keller
 *
 *
 * An entry tree class.
 */
public class EntryTree<K, V> {
    // Dummy root node.
    // Made public for grading.
    public Node root;

    /**
     * You are allowed to add at most TWO more data fields to EntryTree class of int type ONLY if you need to.
     */


    // All made public for grading.
    public class Node implements EntryNode<K, V> {
        public Node child; // reference to the first child node
        public Node parent; // reference to the parent node
        public Node prev; // reference to the previous sibling
        public Node next; // reference to the next sibling
        public K key; // the key for this node
        public V value; // the value at this node

        public Node(K aKey, V aValue) {
            key = aKey;
            value = aValue;
            child = null;
            parent = null;
            prev = null;
            next = null;
        }

        @Override
        public EntryNode<K, V> parent() {
            return parent;
        }

        @Override
        public EntryNode<K, V> child() {
            return child;
        }

        @Override
        public EntryNode<K, V> next() {
            return next;
        }

        @Override
        public EntryNode<K, V> prev() {
            return prev;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }
    }

    public EntryTree() {
        root = new Node(null, null);
    }

    /**
     * Returns the value of the entry with a specified key sequence, or {@code null} if this tree contains no entry with
     * this key sequence.
     *
     * This method returns {@code null} if {@code keyarr} is null or if its length is {@code 0}. If any element of
     * {@code keyarr} is {@code null}, then the method throws a {@code NullPointerException}. The method returns the
     * value of the entry with the key sequence in {@code keyarr} or {@code null} if this tree contains no entry with
     * this key sequence. An example is given in provided sample input and output files to illustrate this method.
     *
     * @param keyarr
     *         Read description.
     *
     * @return Read description.
     *
     * @throws NullPointerException
     *         Read description.
     */
    public V search(K[] keyarr) {
        if (keyarr == null || keyarr.length == 0)
            return null;
        if (containsNull(keyarr))
            throw new NullPointerException();

        Node result = locateNodeP(root, 0, keyarr.length, keyarr);
        return (result == null) ? null : result.value;
    }

    /**
     * This method returns an array of type {@code K[]} with the longest prefix of the key sequence specified in {@code
     * keyarr} such that the keys in the prefix label the nodes on the path from the root to a node. The length of the
     * returned array is the length of the longest prefix.
     *
     * This method returns {@code null} if {@code keyarr} is {@code null}, or if its length is {@code 0}. If any element
     * of {@code keyarr} is {@code null}, then the method throws a {@code NullPointerException}. A prefix of the array
     * {@code keyarr} is a key sequence in the subarray of {@code keyarr} from index {@code 0} to any index {@code
     * m>=0}, i.e., greater than or equal to; the corresponding suffix is a key sequence in the subarray of {@code
     * keyarr} from index {@code m+1} to index {@code keyarr.length-1}. The method returns an array of type {@code K[]}
     * with the longest prefix of the key sequence specified in {@code keyarr} such that the keys in the prefix are,
     * respectively, with the nodes on the path from the root to a node. The lngth of the returned array is the length
     * of the longest prefix. Note that if the length of the longest prefix is {@code 0}, then the method returns {@code
     * null}. This method can be used to select a shorted key sequence for an {@code add} command to create a shorter
     * path of nodes in the tree. An example is given in the attachment to illustrate how this method is used with the
     * {@code #add(K[] keyarr, V aValue)} method.
     *
     * NOTE: In this method you are allowed to use {@link java.util.Arrays}'s {@code copyOf} method only.
     *
     * @param keyarr
     *         Read description.
     *
     * @return Read description.
     *
     * @throws NullPointerException
     *         Read description.
     */
    public K[] prefix(K[] keyarr) {
        if (keyarr == null || keyarr.length == 0)
            return null;
        if (containsNull(keyarr))
            throw new NullPointerException();

        return java.util.Arrays.copyOf(keyarr, longestPrefix(root.child, 0, keyarr));
    }

    /**
     * This method returns {@code false} if {@code keyarr} is {@code null}, its length is {@code 0}, or {@code aValue}
     * is {@code null}. If any element of {@code keyarr} is {@code null}, then the method throws a {@code
     * NullPointerException}.
     *
     * This method locates the node {@code P} corresponding to the longest prefix of the key sequence specified in
     * {@code keyarr} such that the keys in the prefix label the nodes on the path from the root to the node. If the
     * length of the prefix is equal to the length of {@code keyarr}, then the method places {@code aValue} at the node
     * {@code P} (in place of its old value) and returns {@code true}. Otherwise, the method creates a new path of nodes
     * (starting at a node {@code S}) labelled by the corresponding suffix for the prefix, connects the prefix path and
     * suffix path together by making the node {@code S} a child of the node {@code P}, and returns {@code true}. An
     * example input and output files illustrate this method's operation.
     *
     * NOTE: In this method you are allowed to use {@link java.util.Arrays}'s {@code copyOf} method only.
     *
     * @param keyarr
     *         Read description.
     * @param //Read
     *         description.
     *
     * @return Read description.
     *
     * @throws NullPointerException
     *         Read description.
     */
    public boolean add(K[] keyarr, V aValue) {
        if (keyarr == null || keyarr.length == 0 || aValue == null)
            return false;
        if (containsNull(keyarr))
            throw new NullPointerException();

        int sequenceLength = longestPrefix(root.child, 0, keyarr);

        Node p = locateNodeP(root, 0, sequenceLength, keyarr);
        if (sequenceLength == keyarr.length) {
            p.value = aValue;
        }

        for (int i = sequenceLength; i < keyarr.length; i++) {//goes through keyarr making new nodes with k[i] values
            if (p.child() == null) { // if p doesn't have a child
                p.child = new Node(keyarr[i], null);
                p.child.parent = p;
                if (i == keyarr.length - 1)
                    p.child.value = aValue;
                p = p.child; //transitions p to new current
            } else { //In the case p has a child, we must go to end of sibling chain
                Node cur = p.child;
                while (cur.next != null)//goes to end of sibling chain
                    cur = cur.next;
                cur.next = new Node(keyarr[i], null);
                cur.next.parent = p;
                cur.next.prev = cur;
                if (i == keyarr.length - 1)
                    cur.next.value = aValue;
                p = cur.next;//transitions p to new current
            }
        }
        return true;
    }

    /**
     * Removes the entry for a key sequence from this tree and returns its value if it is present. Otherwise, it makes
     * no change to the tree and returns {@code null}.
     *
     * This method returns {@code null} if {@code keyarr} is {@code null} or its length is {@code 0}. If any element of
     * {@code keyarr} is {@code null}, then the method throws a {@code NullPointerException}. The method returns {@code
     * null} if the tree contains no entry with the key sequence specified in {@code keyarr}. Otherwise, the method
     * finds the path with the key sequence, saves the value field of the node at the end of the path, sets the value
     * field to {@code null}.
     *
     * The following rules are used to decide whether the current node and higher nodes on the path need to be removed.
     * The root cannot be removed. Any node whose value is not {@code null} cannot be removed. Consider a non-root node
     * whose value is {@code null}. If the node is a leaf node (has no children), then the node is removed. Otherwise,
     * if the node is the parent of a single child and the child node is removed, then the node is removed. Finally, the
     * method returns the saved old value.
     *
     * @param keyarr
     *         Read description.
     *
     * @return Read description.
     *
     * @throws NullPointerException
     *         Read description.
     */
    public V remove(K[] keyarr) {
        if (keyarr == null || keyarr.length == 0)
            return null;
        if (containsNull(keyarr))
            throw new NullPointerException();
        Node cur = locateNodeP(root, 0, keyarr.length, keyarr);
        if (cur == null || cur == root)
            return null;

        V temp = cur.value();
        cur.value = null;
        while (cur.value == null && cur.child == null && cur != root) {
            if (cur.parent.child != cur) {//if parent doesn't point directly to current, but to its sibling
                cur.prev.next = cur.next;
                if (cur.next != null)
                    cur.next.prev = cur.prev;
            } else {
                cur.parent.child = cur.next;
            }
            cur = cur.parent; //ascend tree
        }

        return temp;
    }


    /**
     * This method prints the tree on the console in the output format shown in provided sample output file. If the tree
     * has no entry, then the method just prints out the line for the dummy root node.
     */
    public void showTree() {
        showTreeRecursion(root, 0, root);
    }

    /**
     * Does the internal work recursively of the showTree method. Read that description
     *
     * @param parent
     *         The node the method begins searching from
     * @param depth
     *         The relative "depth" of the parent node passed in
     * @param upperBound
     *         The upper bound of the recursion. Prevents code from repeating itself when ascending back up through the
     *         tree. Recursion will stop when it reaches upper bound if it does
     */
    private void showTreeRecursion(Node parent, int depth, Node upperBound) {
        int depthCounter = depth;
        Node cur = parent;
        if (cur == null)
            return;
        if (cur == root) { // must do this or can ruin child relationship in second while loop
            System.out.println(cur.key + "::" + cur.value);
            showTreeRecursion(root.child, 0, root);
            return;
        }
        while (cur != null) { //goes down
            periodPrinter(depthCounter);
            System.out.println(cur.key + "::" + cur.value);
            if (cur.child != null) {
                cur = cur.child; //descending iteration
                depthCounter += 2;
            } else
                break; // must break to keep cur node in cur state
        }
        while (cur != null && cur != upperBound) { // goes up, then right. Ensures code stops at upper bound
            if (cur.next != null)
                showTreeRecursion(cur.next, depthCounter, cur.next.parent); // recursively calls on sibling
            cur = cur.parent;
            depthCounter -= 2;
        }
        return;
    }

    /**
     * Returns all values in this entry tree together with their keys. The order of outputs would be similar to level
     * order traversal, i.e., first you would get all values together with their keys in first level from left to right,
     * then second level, and so on. If tree has no values then it would return {@code null}.
     *
     * For the example image given in description, the returned String[][] would look as follows:
     *
     * {{"IA","Grow"}, {"ISU","CS228"}}
     *
     * NOTE: In this method you are allowed to use {@link java.util.LinkedList}.
     */
    public String[][] getAll() {
        java.util.LinkedList<Node> fullList = new java.util.LinkedList<Node>();
        java.util.LinkedList<Node> valueList = new java.util.LinkedList<Node>();
        fullList.addLast(root);
        int valueCounter = 0;
        Node cur = null;

        while (!fullList.isEmpty()) {
            Node removed = fullList.removeFirst();
            if (removed.value != null) {
                valueCounter++;
                valueList.addLast(removed); // adds nodes with value to end of our value list, to maintain order
            }
            if (removed.child != null) {
                cur = removed.child;
                while (cur != null) {
                    fullList.addLast(cur);
                    cur = cur.next;
                }
            }
        }//ends adding to valueList. Should be full of values in order
        if (valueCounter == 0)
            return null;
        else {
            String[][] result = new String[valueCounter][2];
            for (int i = 0; i < valueCounter; i++) {
                cur = valueList.removeFirst();
                result[i][0] = keyReturner(cur);
                result[i][1] = cur.value.toString();
            }
            return result;
        }
    }

    /**
     * Returns the keys in string form in order from root to given node
     *
     * @param givenNode
     *         The node the keys are returned until they reach
     *
     * @return The string representing the keys from root to givenNode
     */
    private String keyReturner(Node givenNode) {
        Node cur = givenNode;
        StringBuilder key = new StringBuilder();
        while (cur != null && cur != root) {
            key.append(cur.key.toString());
            cur = cur.parent;
        }
        key.reverse();
        return key.toString();
    }

    /**
     * Checks whether the passed in K array contains a null index
     *
     * @param keyarr
     *         The array to be searched through
     *
     * @return true if contains null index, false otherwise
     */
    private boolean containsNull(K[] keyarr) {
        for (int i = 0; i < keyarr.length; i++) {
            if (keyarr[i] == null)
                return true;
        }
        return false;
    }

    /**
     * Finds the longest prefix length from the given parent node
     *
     * @param parentNode
     *         The node at which the method beings searching from
     * @param startPoint
     *         The counter starting point of how far through the prefix the method is
     * @param keyarr
     *         The array containing our ideal character sequence
     *
     * @return The length of the longest prefix found
     */
    private int longestPrefix(EntryNode<K, V> parentNode, int startPoint, K[] keyarr) {
        int counter = startPoint;
        int length = keyarr.length;
        EntryNode<K, V> iterator = parentNode;

        if (startPoint == length - 1)
            return startPoint;

        while (iterator != null && counter < length && iterator.key().equals(keyarr[counter])) {
            counter++;
            iterator = iterator.child();
        }
        while (iterator != null && counter < length) {
            if (!iterator.key().equals(keyarr[counter]))
                iterator = iterator.next();
            else
                return longestPrefix(iterator, counter, keyarr);
        }
        return counter;

    }

    /**
     * Locates the node P corresponding to the longest prefix of the key sequence specified by endPoint and keyarr
     *
     * @param parentNode
     *         The node the traversal begins with
     * @param startPoint
     *         The int startPoint that decides the range
     * @param endPoint
     *         The int endpoint that decides when to stop, as endPoint is prefix() method call
     * @param keyarr
     *         The array containing keys we are interested in
     *
     * @return The last node in EntryTree that is holds the key keyarr[endPoint], or null if not found
     */
    private Node locateNodeP(Node parentNode, int startPoint, int endPoint, K[] keyarr) {
        int counter = startPoint;
        Node iterator = parentNode;

        if (startPoint == endPoint)
            return parentNode;

        if (parentNode == root) {
            iterator = root.child;
        }

        while (iterator != null && counter < endPoint && iterator.key().equals(keyarr[counter])) {
            counter++;
            if (counter == endPoint)
                return iterator;
            iterator = (Node) iterator.child();
        }
        while (iterator != null && counter < endPoint) {
            if (!iterator.key().equals(keyarr[counter]))
                iterator = (Node) iterator.next(); //traverses breadth
            else
                return locateNodeP(iterator, counter, endPoint, keyarr); // starts search again
        }
        return null; // should only get to this point if the node is never found
    }

    /**
     * Prints periods on the same line dependent upon given depth
     *
     * @param depth
     *         Amount of periods should print after the initial 6
     */
    private void periodPrinter(int depth) {
        for (int i = 0; i < 7 + depth; i++) {//always prints at least 6 periods
            System.out.print(".");
        }
    }
}
