import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Please enter the constant number: ");
        while (true) {
            String x = in.nextLine();
            if (FormatChecker.canParseDouble(x)) {
                double y = Double.parseDouble(x);
                if (y > 0) {
                    return y;
                }
            }
        }
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */

    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("Please enter a POSTIVE real number: ");
        while (true) {
            String x = in.nextLine();
            if (FormatChecker.canParseDouble(x)) {
                double y = Double.parseDouble(x);
                if (y > 1) {
                    return y;
                }
            }
        }
    }

    /**
     * applies the DeJager Formula.
     *
     *
     * @param con
     * @param w
     * @param e
     * @param r
     * @param t
     * @return double
     */
    private static double[] charmingJager(double con, double w, double e,
            double r, double t) {
        final double[] arr = { -5, -4, -3, -2, -1, -1 / (double) 2,
                -1 / (double) 3, -1 / (double) 4, 0, 1 / (double) 4,
                1 / (double) 3, 1 / (double) 2, 1, 2, 3, 4, 5 };
        double[] bestValues = new double[6];
        double bestValue = 0;
        double bestResult = Double.MAX_VALUE;
        int j = 0;
        while (j < arr.length) {
            int k = 0;
            while (k < arr.length) {
                int l = 0;
                while (l < arr.length) {
                    int x = 0;
                    while (x < arr.length) {
                        double ap = Math.pow(w, arr[j]) * Math.pow(e, arr[k])
                                * Math.pow(r, arr[l]) * Math.pow(t, arr[x]);
                        if ((Math.abs(con - ap)
                                / Math.abs(con)) < (bestResult)) {
                            bestValue = ap;
                            bestResult = (Math.abs(con - ap) / Math.abs(con));
                            bestValues[0] = arr[j];
                            bestValues[1] = arr[k];
                            bestValues[2] = arr[l];
                            bestValues[3] = arr[x];
                            bestValues[4] = bestResult;
                            bestValues[5] = bestValue;
                        }
                        x++;
                    }
                    l++;
                }
                k++;
            }
            j++;
        }
        return bestValues;
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
        double constant = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double e = getPositiveDoubleNotOne(in, out);
        double r = getPositiveDoubleNotOne(in, out);
        double t = getPositiveDoubleNotOne(in, out);

        double[] results = charmingJager(constant, w, e, r, t);
        out.println("Your a, b, c, d components: ");
        int c = 0;
        while (c < results.length - 2) {
            out.print(results[c], 2, false);
            out.print(" ");
            c++;
        }
        out.println();
        out.print("Your best error: ");
        out.println(results[4]);
        out.print("Your guessed result: ");
        out.println(results[5], 2, false);
        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
