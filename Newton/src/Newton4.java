import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * Making Newton ask for a new value of x, and end program when x is negative
 *
 * @author Kwasi Fosu
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        //establishing variables
        double r = x;
        double condition = Math.abs((r * r) - x) / x;
        double num = 0.001;
        if (x > num || x < (-1 * num)) {

            while (condition > (epsilon * epsilon)) {
                r = (r + (x / r)) / 2;
                condition = Math.abs((r * r) - x) / x;

            }

        }

        else {
            r = 0.0;
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
        //attains user inital input
        System.out.print("Enter a number to calculate the square root: ");
        double x = in.nextDouble();

        //loops until input is negative
        if (x >= 0) {
            while (x >= 0) {
                out.print("Enter a number for epsilon: ");
                double epsilon = in.nextDouble();
                System.out.println(sqrt(x, epsilon));

                System.out
                        .print("Enter a number to calculate the square root: ");
                x = in.nextDouble();

            }
        }

        else {
            out.print("Goodbye!");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
