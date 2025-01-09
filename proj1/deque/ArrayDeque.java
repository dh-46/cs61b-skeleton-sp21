package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int capacity = 8;
    private int nextFirst;
    private int nextLast;

    private int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = getUpdatedNextFirst(nextFirst, capacity);

        incrementSize();
    }

    /**
     * @param nextFirst
     * @param capacity
     * @return
     */
    private int getUpdatedNextFirst(int nextFirst, int capacity) {
        return (nextFirst - 1 + capacity) % capacity;
    }

    /**
     * @param nextLast
     * @param capacity
     * @return
     */
    private int getUpdatedNextLast(int nextLast, int capacity) {
        return (nextLast + 1) % capacity;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * RFACTOR);
        }

        items[nextLast] = item;
        nextLast = getUpdatedNextLast(nextLast, capacity);
        incrementSize();
    }

    private void resize(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        int currentIndex = (nextFirst + 1) % capacity;
        int tempIndex = 0;
        int count = size;
        while (count > 0) {
            T item = items[currentIndex];
            temp[tempIndex] = item;
            tempIndex += 1;
            currentIndex = (currentIndex + 1) % capacity;
            count--;
        }

        nextFirst = temp.length - 1;
        nextLast = tempIndex;
        items = temp;
        this.capacity = newCapacity;
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
        int currentIndex = (nextFirst + 1) % capacity;
        while (currentIndex != nextLast) {
            builder.append(items[currentIndex]);
            builder.append(" ");
            currentIndex = (currentIndex + 1) % capacity;
        }
        System.out.println(builder);
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;

        int first = (nextFirst + 1) % capacity;
        T item = items[first];
        items[first] = null;

        nextFirst = nextFirst + 1;
        decrementSize();
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        int last = (nextLast - 1) % capacity;
        T item = items[last];
        items[last] = null;

        nextLast = nextLast - 1;
        decrementSize();
        return item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            // index out of bound
            return null;
        }

        int realIndex = (nextFirst + 1 + index) % capacity;
        return items[realIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    // -------------------------------------------

    /**
     * Size + 1
     */
    private void incrementSize() {
        size += 1;
    }

    /**
     * Size - 1
     */
    private void decrementSize() {
        size -= 1;
    }
}
