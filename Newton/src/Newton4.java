import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Prompts the user for a positive double value to square root Computes estimate
 * of square root of x to within relative error 0.01%.
 *
 * @author Abderrachid Sghir
 * @version 01-16-2024
 */
public final class Newton4 {
    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * makes a number positive if it is negative. gives you the absolute value
     * of a negative number.
     *
     * @param x
     * @return y
     */
    private static double abs(double x) {
        double y = x;
        if (x < 0) {
            y = y * -1.0;
        }
        return y;
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            errorNumber positive number to compute square root of
     * @param errorNumber
     *            description
     * @return estimate of square root
     */
    private static double sqrt(double x, double errorNumber) {
        double r = x;
        double y = x;
        if (r != 0) {
            while (!((abs(r * r - y) / y) < (errorNumber * errorNumber))) {
                r = 0.5 * (r + y / r);
            }
        }
        return r;
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
        double errorNumber;
        double realNumber;
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        boolean value = true;
        while (value) {
            out.println("Enter a postive number to square root: ");
            realNumber = Double.parseDouble(in.nextLine());
            if (realNumber >= 0) {
                out.println("Please enter the error number: ");
                errorNumber = Double.parseDouble(in.nextLine());
                out.println(sqrt(realNumber, errorNumber));
            } else {
                value = false;
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
