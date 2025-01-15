package flik;

/**
 * An Integer tester created by Flik Enterprises.
 *
 * @author Josh Hug
 */
public class Flik {
    /**
     * @param a Value 1
     * @param b Value 2
     * @return Whether a and b are the same
     */
    public static boolean isSameNumber(Integer a, Integer b) {
        // if both a and b are null, it will return true;
        if (a == null && b == null) {
            return true;
        }

        if (a != null) {
            return a.equals(b);
        }

        return false;
    }
}
