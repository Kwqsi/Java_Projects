import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asking a user for a number to generate a square root, with the margin of
 * error being less than epsilon^2
 *
 * @author Kwasi Fosu
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        //establishing variable
        double epsilon = 0.0001;
        double r = x;
        double condition = Math.abs((r * r) - x) / x;

        while (condition > (epsilon * epsilon)) {
            r = (r + (x / r)) / 2;
            condition = Math.abs((r * r) - x) / x;

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
        out.print("Would you like to calculate a square root?: ");
        String answer = in.nextLine();

        if (answer.equals("y")) {
            while (answer.equals("y")) {
                System.out.print("Enter a positive double: ");
                double x = in.nextDouble();
                System.out.println(sqrt(x));
                out.print("Would you like to calculate a square root?: ");
                answer = in.nextLine();
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
