import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumberKernel;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = n1.newInstance();
        temp.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(temp);
    }

    private static void outOfBounds(char[] ch) {
        if (ch.length < 10) {
            throw new ArrayIndexOutOfBoundsException(
                    "Don’t try buffer overflow attacks in Java!");
        }
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {

        n.multiply(new NaturalNumber1L(n));
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int lowEnough = 0;
        int tooHigh = n + 1;
        int guess = (lowEnough + tooHigh) / 2;
        while (tooHigh - lowEnough > 1) {
            if (Math.pow(guess, p) > n) {
                tooHigh = guess;
            } else {
                lowEnough = guess;
            }
            guess = (lowEnough + tooHigh) / 2;
        }
        return lowEnough;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        NaturalNumberKernel nm = new NaturalNumber1L(10);
        myMethod();
        NaturalNumber x = new NaturalNumber1L(10);
        NaturalNumber y = new NaturalNumber1L(25);
        swapNN(x, y);
        NaturalNumber k = new NaturalNumber1L(10);
        square(k);
        out.println(power(2, 2));
        //out.println(x);
        //out.println(y);
        //out.println(k);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
