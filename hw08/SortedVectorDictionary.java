package edu.iastate.cs228.hw08;


import java.util.Vector;
import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;

/**
 * A class that implements a sorted dictionary by using a Vector. Entries are sorted based on keys in nondecreasing
 * order. The dictionary has distinct search keys, i.e., can have duplicate values, however, those are differentiated
 * based on their keys.
 *
 * @author Lucas Keller
 * <p>
 * 0. Put your Firstname and Lastname after above empty author tag. Make sure that in both cases the first letter is
 * uppercase and all others are lowercase. 1. You are allowed to create and use your own private helper methods. If you
 * are introducing your own helper methods those need to be private and properly documented as per Javadoc style.
 * Already existing methods declaration cannot be changed, too. 2. No additional data fields can be introduced in any of
 * the classes below. You are not allowed to change the case of the ones already existing, or rename those. 3. No custom
 * classes of your own can be introduced or used. 4. Import statements are not allowed, besides the ones that are
 * already provided. 5. Fully qualified class names usage is not allowed. 6. You are allowed to reuse any part of the
 * provided source codes or shown under lecture notes section of Canvas, which do not violate any of above. 7. If you
 * have any additional questions PLEASE ask on Piazza Q/A platform, but first PLEASE search and make sure that it was
 * not already asked and answered. PLEASE setup your notifications for both Canvas and Piazza so that you are updated
 * whenever there are any changes immediately. 8. You need to provide implementation to all methods and constructors
 * which have a comment //TODO in their body. For all of these methods and constructors there is no need to provide
 * comments.
 */

public class SortedVectorDictionary<K extends Comparable<? super K>, V>
        implements DictionaryInterface<K, V> {
    private Vector<Entry> dict;

    public SortedVectorDictionary() {
        dict = new Vector<>();
    }

    public SortedVectorDictionary(int initialCapacity) {
        dict = new Vector<>(initialCapacity);
    }

    public V add(K key, V value) {
        if (Objects.isNull(key) || Objects.isNull(value))
            throw new IllegalArgumentException();

        V result = null;
        int addIndex = locateIndex1(key);

        if((addIndex < getSize()) && dict.get(addIndex).getKey().compareTo(key) == 0) {//if replacing old value into equal keys
            result = dict.get(addIndex).getValue();
            dict.get(addIndex).setValue(value);
        }
        else{
            dict.add(addIndex, new Entry(key, value));
            dict.ensureCapacity(getSize() + 1); //makes sure there is always room
        }
        return result;
    }

    public V remove(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();
        V result = null;
        int removeIndex = locateIndex2(key);
        if(removeIndex < 0)
            return null;
        else if(dict.get(removeIndex).getKey().equals(key)){ //ensures found key is == to passed in key, because locate Index returns a number when not found
            result = dict.get(removeIndex).getValue();
            dict.remove(removeIndex);
        }
        return result;
    }

    public V getValue(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();
        int valueIndex = locateIndex2(key);
        if(valueIndex< 0)
            return null;
        else
            return dict.get(valueIndex).getValue();

    }

    public boolean contains(K key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException();
        return locateIndex2(key) >= 0;
    }

    /**
     * Locates the index for the addition of a key
     *
     * @param key
     *         The key to be searched for
     *
     * @return The index of the location for the key to be added
     */
    private int locateIndex1(K key) {
        int counter = 0;
        while (counter < getSize() && key.compareTo(dict.get(counter).getKey()) > 0)
            counter++;
        return counter;
    }

    /**
     * Locates the index of a specific key
     * @param key
     *          They key to be searched for
     * @return
     *          The exact index of our sought after key, or the negative location of where it should be
     */
    private int locateIndex2(K key) {
        int low = 0;
        int high = getSize() - 1;
        while (high >= low) {
            int mid = low + (high - low) / 2;
            if (key.compareTo(dict.get(mid).getKey()) == 0)
                return mid;
            else if (key.compareTo(dict.get(mid).getKey()) > 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -low -1;
    }
    
    public boolean isEmpty() {
        return dict.size() == 0;
    }

    public int getSize() {
        return dict.size();
    }

    public void clear() {
        dict.clear();
    }

    public String toString() {
        return dict.toString();
    }

    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry> iter;

        private KeyIterator() {
            iter = dict.iterator();
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public K next() {
            return iter.next().getKey();
        }
    }

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry> iter;

        private ValueIterator() {
            iter = dict.iterator();
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public V next() {
            return iter.next().getValue();
        }
    }

    private class Entry {
        private K key;
        private V value;

        private Entry(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V dataValue) {
            value = dataValue;
        }

        public String toString() {
            return "(" + key + ":" + value + ")";
        }
    }
}
		
