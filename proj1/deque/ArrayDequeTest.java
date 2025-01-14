package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class ArrayDequeTest {

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     **/
    @Test
    public void addIsEmptySizeTest() {

        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();

        assertTrue("A newly initialized ArrayDeque should be empty", arrayDeque.isEmpty());
        arrayDeque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrayDeque.size());
        assertFalse("arrayDeque should now contain 1 item", arrayDeque.isEmpty());

        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals(3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("arrayDeque should be empty upon initialization", arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        // should not be empty
        assertFalse("arrayDeque should contain 1 item", arrayDeque.isEmpty());

        arrayDeque.removeFirst();
        // should be empty
        assertTrue("arrayDeque should be empty after removal", arrayDeque.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(3);

        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();

        int size = arrayDeque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> arrayDeque1 = new ArrayDeque<String>();
        ArrayDeque<Double> arrayDeque2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrayDeque3 = new ArrayDeque<Boolean>();

        arrayDeque1.addFirst("string");
        arrayDeque2.addFirst(3.14159);
        arrayDeque3.addFirst(true);

        String s = arrayDeque1.removeFirst();
        double d = arrayDeque2.removeFirst();
        boolean b = arrayDeque3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, arrayDeque.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, arrayDeque.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrayDeque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeLast(), 0.0);
        }

    }

    /**
     * Add 5 items to deque and check if target index item equals expected
     */
    @Test
    public void getItemTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        arrayDeque.addLast("item-1");
        arrayDeque.addLast("item-2");
        arrayDeque.addLast("item-3");
        arrayDeque.addLast("item-4");
        arrayDeque.addLast("item-5");
        arrayDeque.addLast("item-6");
        arrayDeque.addLast("item-7");
        arrayDeque.addLast("item-8");
        arrayDeque.addFirst("item-0");

        arrayDeque.printDeque();

        assertEquals("item-3", arrayDeque.get(3));
    }

    /**
     * Add 5 items to deque and get item with invalid index
     * method should return null
     */
    @Test
    public void getItemIsNullTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        arrayDeque.addLast("item-1");
        arrayDeque.addLast("item-2");
        arrayDeque.addLast("item-3");
        arrayDeque.addLast("item-4");
        arrayDeque.addFirst("item-0");

        arrayDeque.printDeque();

        assertNull(arrayDeque.get(-1));
        assertNull(arrayDeque.get(5));
    }

    /**
     * Enhanced for loop
     */
    @Test
    public void iteratorTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast("item-0");
        arrayDeque.addLast("item-1");
        arrayDeque.addLast("item-2");
        arrayDeque.addLast("item-3");
        arrayDeque.addLast("item-4");
        arrayDeque.addLast("item-5");

        String expected = "item-0,item-1,item-2,item-3,item-4,item-5,";

        StringBuilder builder = new StringBuilder();

        for (String item : arrayDeque) {
            builder.append(item);
            builder.append(",");
        }

        String result = builder.toString();
        System.out.println(result);

        assertEquals(result, expected);
    }

    /**
     * equals() 兩者相同
     */
    @Test
    public void equalsTest_is_same() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addLast("item-0");

        ArrayDeque<String> b1 = new ArrayDeque<>();
        b1.addLast("item-0");

        assertEquals(a1, b1);
    }

    /**
     * equals() 陣列皆為空
     */
    @Test
    public void equalsTest_both_empty_is_same() {
        ArrayDeque<String> a1 = new ArrayDeque<>();

        ArrayDeque<String> b1 = new ArrayDeque<>();

        assertEquals(a1, b1);
    }

    /**
     * equals() 兩者不同
     */
    @Test
    public void equalsTest_is_not_the_same() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addLast("item-0");
        a1.addLast("item-1");

        ArrayDeque<String> b1 = new ArrayDeque<>();
        b1.addLast("item-0");

        assertNotEquals(a1, b1);
    }

    /**
     * equals() 兩者不同
     */
    @Test
    public void equalsTest_is_the_same_dog() {
        ArrayDeque<Dog> a1 = new ArrayDeque<>();
        a1.addLast(new Dog(1, "Amy"));

        ArrayDeque<Dog> b1 = new ArrayDeque<>();
        b1.addLast(new Dog(1, "Amy"));

        assertEquals(a1, b1);
    }

    /**
     * equals() 泛型類別不同
     */
    @Test
    public void equalsTest_is_differentType() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addLast("item-0");
        a1.addLast("item-1");

        ArrayDeque<Integer> b1 = new ArrayDeque<>();
        b1.addLast(12);

        assertNotEquals(a1, b1);
    }

    /**
     * equals() 不同 Deque 實作但相同內容
     */
    @Test
    public void equalsTest_is_differentDequeImplement_but_with_same_items() {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addLast("item-0");
        a1.addLast("item-1");

        LinkedListDeque<String> b1 = new LinkedListDeque<>();
        b1.addLast("item-0");
        b1.addLast("item-1");

        assertEquals(a1, b1);
    }
}
