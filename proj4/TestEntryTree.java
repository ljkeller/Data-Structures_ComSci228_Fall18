package edu.iastate.cs228.proj4;

import static org.junit.Assert.*;

/**
 * @author Lucas Keller
 *
 * Tests EntryTrees public methods in various ways.
 */
public class TestEntryTree {

    /**
     * Tests the EntryTree class's search method with multiple standard inputs
     */
    @org.junit.Test
    public void search() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        Character[] editSearch = new Character[]{'e', 'd', 'i', 't'};
        assertEquals(testRun1.search(editSearch), "revise");

        Character[] nonExistent = new Character[]{'z', 'm', 'r', 'q'};
        assertNull(testRun1.search(nonExistent));

        assertNull(testRun1.search(new Character[]{'e', 'd', 'i'}));

        assertEquals(testRun1.search(edictTest), "order");

    }

    /**
     * Tests the EntryTree class's prefix method with a moderately difficult case. I had written more cases for this, but that was before I wrote add, so I deleted them.
     */
    @org.junit.Test
    public void prefix() {

        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        assertArrayEquals(new Character[]{'e', 'd', 'i', 't', 'e'}, testRun1.prefix(new Character[]{'e', 'd', 'i',
                't', 'e'}));

        assertNull(testRun1.prefix(new Character[]{})); //tests null case
    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add1() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");
        assertTrue(testRun1.root.key == null);
        assertTrue(testRun1.root.value == null);
        assertTrue(testRun1.root.prev == null);
        assertTrue(testRun1.root.next == null);

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "change");

    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add2() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");
        assertTrue(testRun1.root.key == null);
        assertTrue(testRun1.root.value == null);
        assertTrue(testRun1.root.prev == null);
        assertTrue(testRun1.root.next == null);

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");
        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "change");
        assertEquals(testRun1.root.child.next.key, (Character) 'E');
        assertEquals(testRun1.root.child.next.value, null);
        assertEquals(testRun1.root.child.next.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.next.child.value, null);
        assertEquals(testRun1.root.child.next.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.next.child.child.value, null);
        assertEquals(testRun1.root.child.next.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.next.child.child.child.value, "changing");

    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add3() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");
        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.next.key, (Character) 'E');
        assertEquals(testRun1.root.child.next.value, null);
        assertEquals(testRun1.root.child.next.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.next.child.value, null);
        assertEquals(testRun1.root.child.next.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.next.child.child.value, null);
        assertEquals(testRun1.root.child.next.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.next.child.child.child.value, "changing");

    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add4() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "revised");
    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add5() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.child.child.child.next.key, (Character) 'c');
        assertEquals(testRun1.root.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.next.child.value, "order");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "revised");

    }

    /**
     * Tests the EntryTree class's add method in chronological order of the first 6 steps in infile.txt
     */
    @org.junit.Test
    public void add6() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.child.child.child.next.key, (Character) 'c');
        assertEquals(testRun1.root.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.next.child.value, "order");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "revised");
        assertEquals(testRun1.root.child.child.child.child.child.next.key, (Character) 'o');
        assertEquals(testRun1.root.child.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.next.child.key, (Character) 'r');
        assertEquals(testRun1.root.child.child.child.child.child.next.child.value, "writer");
    }

    /**
     * Tests the EntryTree class's remove method with scaling test difficulty, 1-5
     */
    @org.junit.Test
    public void remove1() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");
        assertTrue(testRun1.root.key == null);
        assertTrue(testRun1.root.value == null);
        assertTrue(testRun1.root.prev == null);
        assertTrue(testRun1.root.next == null);

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "change");

        testRun1.remove(editTest);
        assertNull(testRun1.root.child);
    }

    /**
     * Tests the EntryTree class's remove method with scaling test difficulty, 1-5
     */
    @org.junit.Test
    public void remove2() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");
        assertTrue(testRun1.root.key == null);
        assertTrue(testRun1.root.value == null);
        assertTrue(testRun1.root.prev == null);
        assertTrue(testRun1.root.next == null);

        assertNull(testRun1.remove(new Character[]{'e'}));
        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "change");
    }

    /**
     * Tests the EntryTree class's remove method with scaling test difficulty, 1-5
     */
    @org.junit.Test
    public void remove3() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        assertEquals(testRun1.remove(editTest), "revise");
        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.key, (Character) 'c');
        assertEquals(testRun1.root.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.next.child.value, "order");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "revised");
        assertEquals(testRun1.root.child.child.child.child.child.next.key, (Character) 'o');
        assertEquals(testRun1.root.child.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.next.child.key, (Character) 'r');
        assertEquals(testRun1.root.child.child.child.child.child.next.child.value, "writer");
    }

    /**
     * Tests the EntryTree class's remove method with scaling test difficulty, 1-5
     */
    @org.junit.Test
    public void remove4() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        assertEquals(testRun1.remove(editedTest), "revised");

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.child.child.child.next.key, (Character) 'c');
        assertEquals(testRun1.root.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.next.child.value, "order");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'o');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'r');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "writer");
        assertNull(testRun1.root.child.child.child.child.child.next);

    }

    /**
     * Tests the EntryTree class's remove method with scaling test difficulty, 1-5
     */
    @org.junit.Test
    public void remove5() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        assertEquals(testRun1.remove(editorTest), "writer");

        assertEquals(testRun1.root.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.value, null);
        assertEquals(testRun1.root.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.key, (Character) 'i');
        assertEquals(testRun1.root.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.value, "revise");
        assertEquals(testRun1.root.child.child.child.child.next.key, (Character) 'c');
        assertEquals(testRun1.root.child.child.child.child.next.value, null);
        assertEquals(testRun1.root.child.child.child.child.next.child.key, (Character) 't');
        assertEquals(testRun1.root.child.child.child.child.next.child.value, "order");
        assertEquals(testRun1.root.child.child.child.child.child.key, (Character) 'e');
        assertEquals(testRun1.root.child.child.child.child.child.value, null);
        assertEquals(testRun1.root.child.child.child.child.child.child.key, (Character) 'd');
        assertEquals(testRun1.root.child.child.child.child.child.child.value, "revised");
        assertNull(testRun1.root.child.child.child.child.child.next);
    }


    /**
     * Tests EntryTree class's showTree method by looking at the output. Thus, it is commented out because it is not a
     * true junit test, and the TA's will likely deduct points if it does output results. It does function though
     */
    @org.junit.Test
    public void showTree() {
//        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
//        EntryTree<Character, String> testRun1 = new EntryTree<>();
//        testRun1.add(editTest, "change");
//
//        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
//        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
//        testRun1.add(EditTest, "changing");
//
//        testRun1.add(editTest, "revise");
//
//        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
//        testRun1.add(editedTest, "revised");
//
//        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
//        testRun1.add(edictTest, "order");
//
//        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
//        testRun1.add(editorTest, "writer");
//
//        testRun1.showTree();
    }

    /**
     * Tests the EntryTree class's getAll method with scaling test difficulty, 1-2
     */
    @org.junit.Test
    public void getAll1() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        assertArrayEquals(new String[][]{{"edit", "change"}, {"Edit", "changing"}}, testRun1.getAll());
    }

    /**
     * Tests the EntryTree class's getAll method with scaling test difficulty, 1-2
     */
    @org.junit.Test
    public void getAll2() {
        Character[] editTest = new Character[]{'e', 'd', 'i', 't'};
        EntryTree<Character, String> testRun1 = new EntryTree<>();
        testRun1.add(editTest, "change");

        //Tests if adding "Edit" affected first subtree, then ensures it added a new one to correct spot
        Character[] EditTest = new Character[]{'E', 'd', 'i', 't'};
        testRun1.add(EditTest, "changing");

        testRun1.add(editTest, "revise");

        Character[] editedTest = new Character[]{'e', 'd', 'i', 't', 'e', 'd'};
        testRun1.add(editedTest, "revised");

        Character[] edictTest = new Character[]{'e', 'd', 'i', 'c', 't'};
        testRun1.add(edictTest, "order");

        Character[] editorTest = new Character[]{'e', 'd', 'i', 't', 'o', 'r'};
        testRun1.add(editorTest, "writer");

        assertArrayEquals(new String[][]{{"edit", "revise"}, {"Edit", "changing"}, {"edict", "order"}, {"edited",
                "revised"}, {"editor", "writer"}}, testRun1.getAll());
    }

}