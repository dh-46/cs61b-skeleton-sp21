package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    public class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item) {
            this.item = item;
            this.prev = this;
            this.next = this;
        }
    }

    private Node sentinelNode;
    private int size;

    public LinkedListDeque() {
        sentinelNode = new Node(null);
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node insert = new Node(item);
        Node currentFirstNode = sentinelNode.next;

        insert.prev = sentinelNode;
        insert.next = currentFirstNode;

        sentinelNode.next = insert;

        // Was empty list
        if (sentinelNode.prev == sentinelNode) {
            sentinelNode.prev = insert;
        }

        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node insert = new Node(item);
        Node currentLast = sentinelNode.prev;
        // 串在最後一位後面
        currentLast.next = insert;
        insert.prev = currentLast;
        insert.next = sentinelNode;
        sentinelNode.prev = insert;

        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder printBuilder = new StringBuilder();
        Node pointer = sentinelNode.next;

        while (pointer.item != null) {
            printBuilder.append(pointer.item);

            if (pointer.next != sentinelNode) {
                printBuilder.append(" ");
            }

            pointer = pointer.next;
        }
        System.out.println(printBuilder);
    }

    @Override
    public T removeFirst() {
        Node first = sentinelNode.next;
        if (first == sentinelNode) {
            return null;
        }

        Node second = first.next;

        sentinelNode.next = second;
        second.prev = sentinelNode;

        size -= 1;

        return first.item;
    }

    @Override
    public T removeLast() {
        Node last = sentinelNode.prev;
        if (last == sentinelNode) {
            return null;
        }

        Node secondToLast = last.prev;
        secondToLast.next = sentinelNode;
        sentinelNode.prev = secondToLast;

        size -= 1;

        return last.item;
    }

    @Override
    public T get(int index) {
        Node pointer = sentinelNode;
        for (int i = 0; i <= index; i++) {
            pointer = pointer.next;
        }

        if (pointer == sentinelNode) {
            return null;
        }

        return pointer.item;
    }

    /**
     * Same as get, but uses recursion.
     *
     * @param index position of node
     * @return node
     */
    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }

        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursive(sentinelNode.next, index).item;
    }

    /**
     * Get node recursively
     *
     * @param node  current node
     * @param index target index
     * @return node
     */
    private Node getRecursive(Node node, int index) {
        if (index == 0) {
            return node;
        }

        return getRecursive(node.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int index = 0;

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

    @Override
    public boolean equals(Object obj) {
        // 如果是同一個指向的物件那就不用再檢查了 (有助於效能)
        if (obj == this) {
            return true;
        }

        // 不同型別
        if (!(obj instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<?> otherList = (LinkedListDeque<?>) obj;

        // 數量不同
        if (otherList.size != this.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            T item = get(i);
            T other = (T) otherList.get(i);

            if (item != other) {
                return false;
            }
        }

        return true;
    }
}
