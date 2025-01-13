package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {

    /**
     *
     */
    @Test
    public void findMaxConstructorComparatorTest() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(new IntegerComparator());
        maxArrayDeque.addLast(1);
        maxArrayDeque.addLast(2);
        maxArrayDeque.addLast(4);

        Integer max = maxArrayDeque.max();

        Assert.assertEquals(Integer.valueOf(4), max);
    }

    @Test
    public void findMaxDequeIsEmptyTest() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(new IntegerComparator());

        Integer max = maxArrayDeque.max();
        Assert.assertNull(max);
    }

    @Test
    public void findMaxParamComparatorTest() {
        MaxArrayDeque<String> maxArrayDeque = new MaxArrayDeque<>(null);

        maxArrayDeque.addLast("a");
        maxArrayDeque.addLast("b");
        maxArrayDeque.addLast("c");
        maxArrayDeque.addLast("d");

        String max = maxArrayDeque.max(new StringComparator());

        Assert.assertEquals("d", max);
    }

    // ----------------------------------------------------

    private static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    private static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
}
