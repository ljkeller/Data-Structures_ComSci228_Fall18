package edu.iastate.cs228.hw08;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A class that implements the ADT dictionary by using a chain of nodes. The dictionary is unsorted and has distinct
 * search keys, i.e., can have duplicate values, however, those are differentiated based on their keys.
 *
 * @author Lucas Keller
 *
 * Exactly same as the ones listed for SortedVectorDictionary class.
 *
 * In addition to above ANSWER the following 6 QUESTIONS, inside these comments right below each question. Figures
 * needed to answer questions 3, 4, and 5 are shown on Canvas under description of HW08.
 *
 * ========================================================================= Q1. (a) What is the height of the shortest
 * binary tree that contains 22 nodes? (b) Is this tree full? (c) Is it balanced?
 *
 * A1. (a) The height of the shorted binary tree * that contains 22 nodes is 5. (b) The tree is not full. (c) The tree
 * is balanced. ========================================================================= Q2. Consider a binary tree
 * that has four levels. (a) What is the maximum number of nodes in this tree? (b) What is the maximum number of leaves
 * in this tree?
 *
 * A2. (a) The maximum number of nodes in the tree would be a full tree - 2^4 - 1 = 15 nodes. (b) The maximum number *
 * of leaves in this tree would be its bottom level- so 8 leaves.
 * =========================================================================
 * Q3. Consider a traversal of a binary tree, which contains Integer data. Suppose that visiting a node means to simply
 * display the data in the node. What are the results of each of the following traversals of the binary tree shown in
 * Figure 1. (a) Preorder (b) Postorder (c) Inorder (d) Level order
 *
 * A3. (a) 6, 4, 2, 1, 3, 5, 8, 7, 9, 10, 11 (b) 1, 3, 2, 5, 4, 9, 7, 11, 10, 8, 6 (c) 1, 2, 3, 4, 5, 6, 7, 9, 8, 10, 11
 * (d) 6, 4, 8, 2, 5, 7, 10, 1, 3, 9, 11
 *
 * ========================================================================= Q4. Repeat Q3 but for the binary tree shwn
 * in Figure 2. A4. (a) 11, 8, 3, 2, 1, 5, 4, 6, 10, 9, 7 (b) 2, 1, 3, 4, 6, 5, 8, 9, 7, 10, 11 (c) 2, 3, 1, 8, 4, 5, 6,
 * 11, 9, 10, 7 (d) 11, 8, 10, 3, 5, 9, 7, 2, 1, 4, 6
 *
 * ========================================================================= Q5. The two binary trees shown in Figures 1
 * and 2 contain Integer data. (a) Is the tree in Figure 1 a binary search tree? Why or why not? (b) Is the tree in
 * Figure 2 a maxheap? Why or why not?
 *
 * A5. (a) The tree in figure 1 is not a binary search tree, due to the the right subtree having a value of 9 in a left
 * subtree of a node with data 8. In a non-duplicate binary search tree, nodes must be greater than all data in their
 * left subtree. (b) The tree in figure 2 is not a maxheap, due to the right leaf of the subtree containting 5. The node
 * 5 is less than the node in its right subtree, causing it to not be a maxheap. Maxheaps require all descendent nodes
 * to be less than their value.
 *
 * ========================================================================= Q6. Can a binary search tree ever be a
 * maxheap? Explain. A6 The requirements for a complete tree are that the tree is full until the last level, has atleast
 * 1 node, and is then filled left to right. The requirements for a binary search tree is just that there are at most
 * two branches from a node, and that the left subtree is less than the parent, while the right is greater. With those
 * facts in mind, a tree with just 2 nodes, one a max, and one less than it to the left, should both a maxheap and a
 * binary search tree.
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {
    private Node firstNode;   // Reference to first node of chain
    private int numberOfEntries;

    public LinkedDictionary() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public V add(K key, V value) {
        if (Objects.isNull(key) || Objects.isNull(value))
            throw new IllegalArgumentException();
        V result = null;

        Node locatedNode = locateNode(key);
        if (locatedNode != null) {
            result = locatedNode.getValue();
            locatedNode.value = value;
            return result;
        }
        Node addNode = new Node(key, value, firstNode);
        firstNode = addNode;
        numberOfEntries++;

        return result;
    }

    public V remove(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();

        V result = null;
        Node foundNode = locateNodeBefore(key);
        if (foundNode == null) // if node isn't located
            return null;

        if (foundNode == firstNode && firstNode.getKey().equals(key)) { //In the case that the node to be removed is
            // first node, locateNode Before will find first node
            result = firstNode.getValue();
            firstNode = firstNode.getNextNode();
        } else {//foundNode = node before key node
            result = foundNode.getNextNode().getValue();
            foundNode.setNextNode(foundNode.getNextNode().getNextNode());
        }
        numberOfEntries--;
        return result;

    }

    public V getValue(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();
        Node holdingNode = locateNode(key);
        return (holdingNode == null) ? null : holdingNode.getValue();
    }

    public boolean contains(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();
        return locateNode(key) != null;
    }

    /**
     * Helper method that locates node that corresponds to given key
     *
     * @param key
     *         the Key being searched for
     *
     * @return The node with the input key, or null if not found
     */
    private Node locateNode(K key) {
        if (firstNode == null)
            return null;
        Node cur = firstNode;
        while (cur != null) {
            if (cur.getKey().equals(key))
                return cur;
            cur = cur.getNextNode();
        }
        return cur; //returns null if not found
    }

    /**
     * Helper method that locates Node before Node corresponding to given key
     *
     * @param key
     *         The key being searched for
     *
     * @return The node before the node corresponding to the given key if size > 1, the node itself if the node has no
     * nodes before it, or null if the node was not found
     */
    private Node locateNodeBefore(K key) {
        if (firstNode == null)
            return null;
        Node cur = firstNode;
        if (cur.getKey().equals(key)) // if first Node is node corresponding to key
            return cur;
        while (cur.getNextNode() != null) {//iterates through list looking for node before node
            if (cur.getNextNode().getKey().equals(key))
                return cur;
            cur = cur.getNextNode();
        }
        return null; //returns null if not found
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public int getSize() {
        return numberOfEntries;
    }

    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    // Needs to output String representation in exact same
    // format as the one done by SortedVectorDictionary.
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        Node cur = firstNode;
        for (int i = 0; i < numberOfEntries - 1; i++) {
            result.append(cur.toString() + " ,");
            cur = cur.getNextNode();
        }
        result.append(cur.toString() + ']');
        return result.toString();
    }

    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private Node nextNode;

        private KeyIterator() {
            nextNode = firstNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public K next() {
            if (!hasNext())
                throw new NoSuchElementException();
            K temp = nextNode.getKey();
            nextNode = nextNode.getNextNode();
            return temp;
        }
    }

    private class ValueIterator implements Iterator<V> {
        private Node nextNode;

        private ValueIterator() {
            nextNode = firstNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            V temp = nextNode.getValue();
            nextNode = nextNode.getNextNode();
            return temp;
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node next;

        private Node(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
        }

        private Node(K searchKey, V dataValue, Node nextNode) {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V newValue) {
            value = newValue;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }

        public String toString() {
            return "(" + key + ":" + value + ")";
        }
    }
}
		
