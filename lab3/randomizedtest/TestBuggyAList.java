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
    public void testThreeAddThreeRemove()  {
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
}
