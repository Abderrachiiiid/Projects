import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class Hailstone1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1() {
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
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber num, SimpleWriter out) {
        NaturalNumber n = new NaturalNumber1L(num);
        NaturalNumber count = new NaturalNumber1L(1);
        NaturalNumber maxNumber = new NaturalNumber1L(n);
        while (n.compareTo(new NaturalNumber1L(1)) != 0) {
            if (maxNumber.compareTo(n) < 0) {
                maxNumber = new NaturalNumber1L(n);
            }
            NaturalNumber x = new NaturalNumber1L(n);
            NaturalNumber r = x.divide(new NaturalNumber1L(2));
            if (r.compareTo(new NaturalNumber1L(0)) != 0) {
                n.multiply(new NaturalNumber1L(3));
                n.add(new NaturalNumber1L(1));
                out.print(n.toString() + " ");
            } else {
                n.divide(new NaturalNumber1L(2));
                out.print(n.toString() + " ");
            }
            count.add(new NaturalNumber1L(1));
        }
        System.out.println("The length of the series is: " + count.toString());
        out.println("The maximum value in this serie is: " + maxNumber.toInt());
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
        out.println("Please enter a positive number: ");
        String str = in.nextLine();
        NaturalNumber num = new NaturalNumber1L(str);
        generateSeries(num, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
