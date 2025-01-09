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
        if (isFull()) {
            resize(capacity * RFACTOR);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

        incrementSize();
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(capacity * RFACTOR);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        incrementSize();
    }

    /**
     * Check if items array is full.
     * @return true if item array is full.
     */
    private boolean isFull() {
        return size == capacity;
    }

    /**
     * Resize the item array
     * @param newCapacity length for the new item array
     */
    private void resize(int newCapacity) {
        // 建立一個新的暫存陣列
        T[] temp = (T[]) new Object[newCapacity];

        // 複製資料到新的陣列，同時從 index 0 開始排序
        for (int i = 0; i < size; i++) {
            temp[i] = items[getItemIndex(i)];
        }

        // 重新排序後第一位為 index 0，所以 nextFirst 會是陣列最後一位。
        nextFirst = temp.length - 1;
        // 最後一位 index 會是目前所儲存資料長度。
        nextLast = size;
        // 替換掉舊資料
        items = temp;
        // 更新資料陣列容量
        capacity = newCapacity;
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

            if (i < size - 1) {
                // Don't the white space for the last item
                builder.append(" ");
            }
        }

        System.out.println(builder);
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;

        // Get current first item
        int first = plusOne(nextFirst);
        T item = items[first];
        // Make first box empty (null)
        items[first] = null;

        // Update nextFirst index by plus 1
        nextFirst = plusOne(nextFirst);
        // Update size
        decrementSize();

        // Return the removed item
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        // Get current last item
        int last = minusOne(nextLast);
        T item = items[last];
        // Make the last box empty (null)
        items[last] = null;
        // Update nextLast index by minus 1
        nextLast = minusOne(nextLast);
        // Update size
        decrementSize();

        // Return the removed item
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            // index out of bound
            return null;
        }

        return items[getItemIndex(index)];
    }

    /**
     * Convert user index to underlying item index
     * 1. nextFirst + 1 -> first
     * 2. % capacity -> convert to item index
     *
     * @param index userIndex
     * @return
     */
    private int getItemIndex(int index) {
        return (nextFirst + 1 + index) % capacity;
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

    /**
     * Minus 1 on index for circular data structure
     * @param index old index
     * @return new index
     */
    private int minusOne(int index) {
        return (index - 1 + capacity) % capacity;
    }

    /**
     * Plus 1 on index for circular data structure
     * @param index old index
     * @return new index
     */
    private int plusOne(int index) {
        return (index + 1) % capacity;
    }
}
