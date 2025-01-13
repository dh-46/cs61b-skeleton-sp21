package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int capacity = 8;
    private int nextFirst;
    private int nextLast;

    private int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * RFACTOR);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        incrementSize();
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * RFACTOR);
        }

        items[nextLast] = item;

        nextLast = plusOne(nextLast);

        incrementSize();
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
        System.out.println("Capacity = " + capacity + "; Size = " + size + "; Ratio = " + ((double) size / capacity));
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;

        if (isUsageRatioLow()) {
            resize(capacity / 2);
        }

        int first = plusOne(nextFirst);
        T item = items[first];
        items[first] = null;

        nextFirst = first;

        decrementSize();

        return item;
    }

    private boolean isUsageRatioLow() {
        if (capacity <= 16) return false;
        double ratio = (double) size / capacity;
        return ratio <= 0.25;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;

        if (isUsageRatioLow()) {
            resize(capacity / 2);
        }

        int last = minusOne(nextLast);
        T item = items[last];
        items[last] = null;

        nextLast = last;

        decrementSize();

        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        return items[getItemIndex(index)];
    }

    // ---------------------------------------------

    private int getItemIndex(int i) {
        return (nextFirst + 1 + i) % capacity;
    }

    private void decrementSize() {
        size -= 1;
    }

    private void incrementSize() {
        size += 1;
    }

    private int minusOne(int index) {
        return (index - 1 + capacity) % capacity;
    }

    private int plusOne(int index) {
        return (index + 1) % capacity;
    }

    private void resize(int newCapacity) {
        T[] tempItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            tempItems[i] = items[getItemIndex(i)];
        }

        nextFirst = tempItems.length - 1;
        nextLast = size;
        capacity = newCapacity;
        items = tempItems;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int index;

        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }
}
