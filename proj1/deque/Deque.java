package deque;

import java.util.Iterator;

/**
 * @see <a href="https://sp21.datastructur.es/materials/proj/proj1/proj1#the-deque-api">The Deque API</a>
 * for more details
 */
public interface Deque<T> {

    /**
     * Add an item of type T to the front of the deque
     * @param item can assume item is never null
     */
    public void addFirst(T item);

    /**
     * Adds an item of type T to the back of the deque.
     * @param item can assume that item is never null.
     */
    public void addLast(T item);

    /**
     * Check is deque empty
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of items in the deque
     * @return deque's size
     */
    public int size();

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque();

    /**
     * Removes and returns the item at the front of the deque.
     * @return If no such item exists, returns null.
     */
    public T removeFirst();

    /**
     * Removes and returns the item at the back of the deque.
     * @return If no such item exists, returns null.
     */
    public T removeLast();

    /**
     * Gets the item at the given index,
     * where 0 is the front,
     * 1 is the next item, and so forth.
     *
     * Must not alter the deque!
     *
     * @param index position of the item
     * @return If no such item exists, returns null.
     */
    public T get(int index);


    public Iterator<T> iterator();

//    /**
//     * Returns whether or not the parameter o is equal to the Deque.
//     *
//     * o is considered equal if it is a Deque and
//     * if it contains the same contents (as goverened by the generic T’s equals method)
//     * in the same order.
//     *
//     * @param o target to compare
//     * @return true if is equal
//     */
//    public boolean equals(Object o);
}
