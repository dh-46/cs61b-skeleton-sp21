package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Return the maximum element in the deque with given Comparator
     * @return null if deque is empty
     */
    public T max() {
        return max(comparator);
    }

    /**
     * Returns the maximum element in the deque with the comparator param
     * @param customComparator custom comparator
     * @return null if deque is empty
     */
    public T max(Comparator<T> customComparator) {
        if (isEmpty()) {
            return null;
        }

        T max = null;
        for (int i = 0; i < size(); i++) {
            T item = get(i);
            if (max == null) {
                max = item;
            } else {
                int compare = customComparator.compare(max, item);
                if (compare < 0) {
                    max = item;
                }
            }
        }
        return max;
    }
}
