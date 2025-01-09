package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int capacity = 8;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        incrementSize();
    }

    private int minusOne(int index) {
        return (index - 1 + capacity) % capacity;
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;

        nextLast = plusOne(nextLast);

        incrementSize();
    }

    private int plusOne(int index) {
        return (index + 1) % capacity;
    }

    private void decrementSize() {
        size -= 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            builder.append(items[getItemIndex(i)]);
            if (i != size - 1) {
                builder.append(" ");
            }
        }

        System.out.println(builder);
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    // ---------------------------------------------

    private int getItemIndex(int i) {
        return (nextFirst + 1 + i) % capacity;
    }

    private void incrementSize() {
        size += 1;
    }
}
