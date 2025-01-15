package flik;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void integerAreNullTest() {
        assertTrue(Flik.isSameNumber(null, null));
    }

    @Test
    public void integerAIsNullTest() {
        assertFalse(Flik.isSameNumber(null, 12));
    }

    @Test
    public void integerBIsNullTest() {
        assertFalse(Flik.isSameNumber(12, null));
    }

    @Test
    public void simpleIntegerTest() {
        assertTrue(Flik.isSameNumber(10, 10));

        assertTrue(Flik.isSameNumber(1, 1));
    }


    @Test
    public void integerFrom0To128Test() {
        int N = 128;
        for (int i = 0; i < N; i++) {
            assertTrue("error when i == " + i, Flik.isSameNumber(i, i));
        }
    }

    @Test
    public void integerFrom128To500Test() {
        int N = 500;
        for (int i = 128; i < N; i++) {
            assertTrue("error when i == " + i, Flik.isSameNumber(i, i));
        }
    }
}
