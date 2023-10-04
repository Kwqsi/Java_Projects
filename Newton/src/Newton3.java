import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Making Newton 3 get user input for epsilon
 *
 * @author Kwasi Fosu
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
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
        out.print("Would you like to calculate a square root?: ");
        String answer = in.nextLine();
//loop including user input for epsilon
        if (answer.equals("y")) {
            while (answer.equals("y")) {
                System.out.print("Enter a positive double: ");
                double x = in.nextDouble();
                out.print("Enter a number for epsilon: ");
                double epsilon = in.nextDouble();
                System.out.println(sqrt(x, epsilon));
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
