package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> broken = new BuggyAList<>();
        AListNoResizing<Integer> correct = new AListNoResizing<>();

        int[] testIntArray = {4, 5, 6};

        for (int i = 0; i < testIntArray.length; i++) {
            int item = testIntArray[i];
            broken.addLast(item);
            correct.addLast(item);
        }

        assertEquals(correct.size(), broken.size());

        for (int i = 0; i < testIntArray.length; i++) {
            int removeFromBroken = broken.removeLast();
            int removeFromCorrect = correct.removeLast();
            assertEquals(removeFromCorrect, removeFromBroken);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int correctSize = correct.size();
                int brokenSize = broken.size();

                assertEquals(correctSize, brokenSize);
            } else if (operationNumber == 2) {
                int size = correct.size();

                if (size > 0) {
                    int correctLast = correct.getLast();
                    int brokenLast = broken.getLast();

                    assertEquals(correctLast, brokenLast);
                }
            } else {
                if (correct.size() > 0 && broken.size() > 0) {
                    int correctRemoveLast = correct.removeLast();
                    int brokenRemoveLast = broken.removeLast();

                    assertEquals(correctRemoveLast, brokenRemoveLast);
                }
            }
        }
    }
}
