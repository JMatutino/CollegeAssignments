package ics311;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

// ------------------------------------------------------------------------------
/**
 * SkipList is an implementation of a data structure for storing a sorted list
 * of data elements, using a hierarchy of linked lists that connect increasingly
 * sparse subsequences of the elements. The elements contained in the tree must
 * be mutually comparable.
 * 
 * @author Michael D. Naper, Jr. <MichaelNaper.com>
 * @version 2010.07.07
 * 
 * @param <Key>
 *            The generic type for data elements contained in the tree.
 */
public class SkipList<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    // ~ Nested Classes ........................................................

    // -------------------------------------------------------------------------
    /**
     * ListNode is an implementation of a skip list node, which stores a data
     * element and references to subsequent nodes.
     */
    private class ListNode {

        // ~ Instance Variables ................................................

        /**
         * The data element stored in this node.
         */
        private Key element;
        
        private Value val;

        /**
         * The references to the subsequent nodes.
         */
        private ListNode[] next;

        // ~ Constructors ......................................................

        // ---------------------------------------------------------------------
        /**
         * Constructs a new instance of ListNode with the specified data element
         * and level.
         * 
         * @param element
         *            The element to be stored in the node.
         * @param level
         *            The level of the node.
         */
        @SuppressWarnings("unchecked")
        public ListNode(Key element, Value val, int level) {

            this.element = element;
            this.val = val;

            next = new SkipList.ListNode[level + 1];
        }

        // ~ Methods ...........................................................

        // ---------------------------------------------------------------------
        /**
         * Returns the level of this ListNode.
         */
        public int level() {

            return next.length - 1;
        }
    }

    // -------------------------------------------------------------------------
    /**
     * ListIterator is an iterator over the SkipList.
     */
    private class ListIterator implements Iterator<Key> {

        // ~ Instance Variables ................................................

        /**
         * The current node in the iteration.
         */
        private ListNode iterNode;

        // ~ Constructors ......................................................

        // ---------------------------------------------------------------------
        /**
         * Constructs a new instance of ListIterator.
         */
        public ListIterator() {

            iterNode = head;
        }

        // ~ Methods ...........................................................

        // ---------------------------------------------------------------------
        /**
         * Returns true if the iterator has more data elements.
         * 
         * @return True if the iterator has more elements.
         */
        @Override
        public boolean hasNext() {

            return iterNode.next[0] != null;
        }

        // ---------------------------------------------------------------------
        /**
         * Returns the next data element in the iteration.
         * 
         * @return The next element in the iteration.
         */
        @Override
        public Key next() {

            iterNode = iterNode.next[0];

            return iterNode.element;
        }

        // ---------------------------------------------------------------------
        /**
         * The removal method is unsupported by this implementation of Iterator.
         */
        @Override
        public void remove() {

            return;
        }
    }

    // ~ Instance Variables ....................................................

    /**
     * The reference to the header node of the list.
     */
    private ListNode head;

    /**
     * The size of the list.
     */
    private int size;

    // ~ Constructors ..........................................................

    // -------------------------------------------------------------------------
    /**
     * Constructs a new, empty instance of SkipList.
     */
    public SkipList() {

        head = new ListNode(null, null, 0);

        size = 0;
    }

    // ~ Methods ...............................................................

    // -------------------------------------------------------------------------
    /**
     * Adds the specified data element to this SkipList, if the element is not
     * already present.
     * 
     * @param element
     *            The element to be inserted.
     * @return True if insertion is performed.
     */
    public Value  add(Key element, Value value) {

        if (element == null) {
            return null;
        }

        int maxLevel = head.level();

        ListNode current = head;

        @SuppressWarnings("unchecked")
        ListNode[] nodePath = new SkipList.ListNode[maxLevel + 1];

        for (int level = maxLevel; level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) > 0) {
                current = current.next[level];
            }

            nodePath[level] = current;
        }

        current = current.next[0];

        if (current != null && element.equals(current.element)) {
            return null;
        } else {
            int nodeLevel = randomLevel();

            if (nodeLevel > maxLevel) {
                head.next = Arrays.copyOf(head.next, nodeLevel + 1);

                nodePath = Arrays.copyOf(nodePath, nodeLevel + 1);

                for (int level = maxLevel + 1; level <= nodeLevel; level++) {
                    nodePath[level] = head;
                }

                maxLevel = nodeLevel;
            }

            current = new ListNode(element, value, nodeLevel);

            for (int level = 0; level <= nodeLevel; level++) {
                current.next[level] = nodePath[level].next[level];

                nodePath[level].next[level] = current;
            }

            size++;

            return value;
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Returns a random level for a new node in the SkipList.
     * 
     * @return A random level for a new node in the SkipList.
     */
    private int randomLevel() {

        int level;

        Random rand = new Random();

        for (level = 0; rand.nextInt() % 2 == 0; level++)
            ;

        return level;
    }


    // -------------------------------------------------------------------------
    /**
     * Returns true if this SkipList contains the specified data element.
     * 
     * @param element
     *            The element whose presence is to be tested.
     * @return True if this SkipList contains the specified element.
     */
    public boolean contains(Key element) {

        if (element == null) {
            return false;
        }

        return find(element) != null;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns true if this SkipList contains all the data elements in the
     * specified Collection.
     * 
     * @param elements
     *            The collection of elements to be checked for containment.
     * @return True if this SkipList contains all the elements in the specified
     *         collection.
     */
    public boolean containsAll(Collection<? extends Key> elements) {

        if (elements == null) {
            return false;
        }

        for (Key element : elements) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns a reference to the specified data element in this SkipList, or
     * null if the element is not present.
     * 
     * @param element
     *            The element to be found.
     * @return The reference to the specified element in the list, or null if
     *         the element is not present.
     */
    public Value find(Key element) {

        if (element == null) {
            return null;
        }

        ListNode current = head;

        for (int level = head.level(); level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) >= 0) {
                current = current.next[level];
            }

            if (element.equals(current.element)) {
                return current.val;
            }
        }

        return null;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the index of the specified data element in this SkipList, or -1
     * if the element is not present.
     * 
     * @param element
     *            The element whose index is to be found.
     * @return The index of the specified data element in this SkipList, or -1
     *         if the element is not present.
     */
    public int indexOf(Key element) {

        if (element == null) {
            return -1;
        }

        ListNode current = head.next[0];

        int pos;

        for (pos = -1; current != null && !element.equals(current.element); pos++) {
            current = current.next[0];
        }

        return (current == null) ? -1 : pos + 1;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the data element at the specified index in this SkipList.
     * 
     * @param index
     *            The index of the element to be returned.
     * @param The
     *            element at the specified index in this SkipList.
     */
    public Key get(int index) {

        if (index < 0 || index > size) {
            return null;
        }

        ListNode current = head.next[0];

        for (int pos = 0; current != null && pos < index; pos++) {
            current = current.next[0];
        }

        return (current == null) ? null : current.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the data element from this SkipList with the smallest key, if the
     * element is present.
     * 
     * @return The element with the smallest key, or null if the list is empty.
     */
    public Key getMin() {

        return (head.next[0] == null) ? null : head.next[0].element;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the data element from this SkipList with the largest key, if the
     * element is present.
     * 
     * @return The element with the largest key, or null if the list is empty.
     */
    public Key getMax() {

        if (head.next[0] == null) {
            return null;
        }

        ListNode current = head;

        for (int level = head.level(); level >= 0; level--) {
            while (current.next[level] != null) {
                current = current.next[level];
            }
        }

        return current.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes the specified data element from this SkipList, if the element is
     * present.
     * 
     * @param element
     *            The element to be removed.
     * @return True if removal is performed.
     */
    public Value remove(Key element) {

        if (element == null) {
            return null;
        }

        int maxLevel = head.level();

        ListNode current = head;

        @SuppressWarnings("unchecked")
        ListNode[] nodePath = new SkipList.ListNode[maxLevel + 1];

        for (int level = maxLevel; level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) > 0) {
                current = current.next[level];
            }

            nodePath[level] = current;
        }

        current = current.next[0];

        if (current != null && element.equals(current.element)) {
            int nodeLevel = current.level();

            for (int level = 0; level <= nodeLevel; level++) {
                if (nodePath[level].next[level] != current) {
                    break;
                }

                nodePath[level].next[level] = current.next[level];
            }

            if (nodeLevel == maxLevel) {
                while (maxLevel > 0 && head.next[maxLevel] == null) {
                    maxLevel--;
                }

                head.next = Arrays.copyOf(head.next, maxLevel + 1);
            }

            size--;

            return null;
        } else {
            return null;
        }
    }


    // -------------------------------------------------------------------------
    /**
     * Removes and returns the data element from this SkipList with the smallest
     * key, if the element is present.
     * 
     * @return The element with the smallest key, or null if the list is empty.
     */
    public Key removeMin() {

        if (head.next[0] == null) {
            return null;
        }

        ListNode firstNode = head.next[0];

        int nodeLevel = firstNode.level();

        for (int level = 0; level <= nodeLevel; level++) {
            head.next[level] = firstNode.next[level];
        }

        int maxLevel = head.level();

        if (nodeLevel == maxLevel) {
            while (maxLevel > 0 && head.next[maxLevel] == null) {
                maxLevel--;
            }

            head.next = Arrays.copyOf(head.next, maxLevel + 1);
        }

        size--;

        return firstNode.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes and returns the data element from this SkipList with the largest
     * key, if the element is present.
     * 
     * @return The element with the smallest key, or null if the list is empty.
     */
    public Key removeMax() {

        if (head.next[0] == null) {
            return null;
        }

        int maxLevel = head.level();

        ListNode current = head;

        @SuppressWarnings("unchecked")
        ListNode[] nodePath = new SkipList.ListNode[maxLevel + 1];

        for (int level = maxLevel; level >= 0; level--) {
            while (current.next[level] != null
                    && current.next[level].next[0] != null) {
                current = current.next[level];
            }

            nodePath[level] = current;
        }

        current = current.next[0];

        int nodeLevel = current.level();

        for (int level = 0; level <= nodeLevel; level++) {
            if (nodePath[level].next[level] != current) {
                break;
            }

            nodePath[level].next[level] = current.next[level];
        }

        if (nodeLevel == maxLevel) {
            while (maxLevel > 0 && head.next[maxLevel] == null) {
                maxLevel--;
            }

            head.next = Arrays.copyOf(head.next, maxLevel + 1);
        }

        size--;

        return current.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes all the data elements from this SkipList. The SkipList will be
     * empty after this method returns.
     */
    public void clear() {

        head = new ListNode(null, null, 0);

        size = 0;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns true if this SkipList contains no data elements. In other words,
     * returns true if the size of this SkipList is zero.
     * 
     * @return True if this SkipList contains no elements.
     */
    public boolean isKeympty() {

        return head.next[0] == null;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the number of data elements in this SkipList.
     * 
     * @return The number of elements in this SkipList.
     */
    public int size() {

        return size;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns an iterator over the data elements in this SkipList in order.
     * 
     * @return An Iterator over the elements in this SkipList.
     */
    public Iterator<Key> iterator() {

        return new ListIterator();
    }

    // -------------------------------------------------------------------------
    /**
     * Returns an array containing all the data elements in this SkipList in
     * order.
     * 
     * @return An array containing all the elements in this SkipList.
     */
    @SuppressWarnings("rawtypes")
    public Comparable[] toArray() {

        Comparable[] result = new Comparable[size];

        ListNode current = head.next[0];

        for (int pos = 0; pos < size; pos++) {
            result[pos] = current.element;

            current = current.next[0];
        }

        return result;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns a string representation of this SkipList. The returned string is
     * a vertical list of the data elements contained in the list.
     * 
     * @return A string representation of this SkipList.
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        ListNode current = head.next[0];

        while (current != null) {
            result.append(current.element + "\n");

            current = current.next[0];
        }

        return result.toString();
    }
    
    
    
    // -------------------------------------------------------------------------
    /*
     * 
     */
    public Key Successor(Key key){
    	int index = this.indexOf(key);
    	
    	if(index == -1)
    		return null;
    	
    	return  this.get(index+1);
    	
    }
    
    public Key Predecessor(Key key){
    	int index = this.indexOf(key);
    	
    	if(index == -1)
    		return null;
    	
    	return this.get(index-1);
    }
       
}