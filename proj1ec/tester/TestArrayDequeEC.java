package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.*;

public class TestArrayDequeEC {

    /**
     * @see StudentArrayDequeLauncher
     */
    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();

        StringBuilder errorMsg = new StringBuilder();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int randomOp = StdRandom.uniform(0, 4);

            if (randomOp == 0) {
                int randomValue = StdRandom.uniform(0, 100);
                correct.addFirst(randomValue);
                student.addFirst(randomValue);

                errorMsg.append("addFirst(" + randomValue + ")\n");

                assertEquals(errorMsg.toString(), correct.get(0), student.get(0));
            } else if (randomOp == 1) {
                int randomValue = StdRandom.uniform(0, 100);
                correct.addLast(randomValue);
                student.addLast(randomValue);

                errorMsg.append("addLast(" + randomValue + ")\n");

                assertEquals(errorMsg.toString(), correct.get(correct.size() - 1), student.get(student.size() - 1));
            } else if (randomOp == 2 && !correct.isEmpty() && !student.isEmpty()) {
                Integer expected = correct.removeFirst();
                Integer actual = student.removeFirst();

                errorMsg.append("removeFirst()\n");

                assertEquals(errorMsg.toString(), expected, actual);
            } else if (randomOp == 3 && !correct.isEmpty() && !student.isEmpty()) {
                Integer expected = correct.removeLast();
                Integer actual = student.removeLast();

                errorMsg.append("removeLast()\n");

                assertEquals(errorMsg.toString(), expected, actual);
            }
        }

    }
}
