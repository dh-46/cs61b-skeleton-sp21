package gh2;

import deque.ArrayDeque;
import deque.Deque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = createBuffer(getCapacity(frequency));
    }

    /**
     * Create new buffer with capacity
     * @param capacity size of the buffer
     * @return new buffer
     */
    private Deque<Double> createBuffer(int capacity) {
        Deque<Double> newBuffer = new ArrayDeque<>();

        for (int i = 0; i < capacity; i++) {
            newBuffer.addLast(0.0);
        }

        return newBuffer;
    }

    /**
     * Get capacity
     * capacity = SR / frequency.
     * You'll need to cast the result of this division operation into an int.
     * For better accuracy, use the Math.round() function before casting.
     * Your should initially fill your buffer array with zeros.
     *
     * @param frequency
     * @return capacity
     */
    private int getCapacity(double frequency) {
        return (int) Math.round(SR / frequency);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in buffer, and replace with random numbers
        // between -0.5 and 0.5. You can get such a number by using:
        // double r = Math.random() - 0.5;
        //
        // Make sure that your random numbers are different from each
        // other. This does not mean that you need to check that the numbers
        // are different from each other. It means you should repeatedly call
        // Math.random() - 0.5 to generate new random numbers for each array index.

        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.
        // **Do not call StdAudio.play().**
        double frontSample = buffer.removeFirst();
        double nextDouble = buffer.get(0);
        double newSample = DECAY * (frontSample + nextDouble) / 2;
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // Return the correct thing.
        return buffer.get(0);
    }
}
