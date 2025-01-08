package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

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
    public boolean isEmpty() {
        return sentinelNode.next == sentinelNode;
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

        if (pointer == sentinelNode) return null;

        return pointer.item;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    /**
     * Same as get, but uses recursion.
     *
     * @param index position of node
     * @return node
     */
    public T getRecursive(int index) {
        if (size == 0) return null;

        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursive(sentinelNode.next, index).item;
    }

    /**
     * Get node recursively
     * @param node current node
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
    public boolean equals(Object obj) {
//        TODO:
//        // 不同型別
//        if (!(obj instanceof LinkedListDeque)) return false;
//
//        // 數量不同
//        if (((LinkedListDeque<?>) obj).size != this.size) return false;
//
//        for (int i = 0; i < ; i++) {
//
//        }
        return super.equals(obj);
    }
}
