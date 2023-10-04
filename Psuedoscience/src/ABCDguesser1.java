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
public final class ABCDguesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDguesser1() {
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
        double answer;
        while (true) {
            out.print("Enter a positive double for your constant: ");
            String input = in.nextLine();
            if (FormatChecker.canParseDouble(input)
                    && (answer = Double.parseDouble(input)) > 0) {
                return answer;
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
        double answer;
        while (true) {
            out.print("Enter a positive double: ");
            String input = in.nextLine();
            if ((FormatChecker.canParseDouble(input)
                    && (answer = Double.parseDouble(input)) > 0)
                    && answer != 1) {
                return answer;
            }
        }

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

        double relativeError = 1;

        double constant = (getPositiveDouble(in, out));
        double w = (getPositiveDoubleNotOne(in, out));
        double x = (getPositiveDoubleNotOne(in, out));
        double y = (getPositiveDoubleNotOne(in, out));
        double z = (getPositiveDoubleNotOne(in, out));
        //initializing array of exponents

        double array[] = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };

        //initializing variables used in the loop
        double closestProduct = 0;

        int arrayLength = 17;
        int[] arrayLoop = new int[4];
        int[] Exponents = new int[4];
//big loop statement that begins from index 0 of arrayLoop
        while (arrayLoop[0] < arrayLength) {
//initializing variables inside the loop
            double diff = relativeError;
            double product = constant;
            arrayLoop[1] = 0;
            while (arrayLoop[1] < arrayLength) {
                arrayLoop[2] = 0;

                while (arrayLoop[2] < arrayLength) {
                    arrayLoop[3] = 0;
                    while (arrayLoop[3] < arrayLength) {
                        //inside the deepest loop calculating the product of the combination and comparing it to other approximations
                        product = Math.pow(w, array[arrayLoop[0]])
                                * Math.pow(x, array[arrayLoop[1]])
                                * Math.pow(y, array[arrayLoop[2]])
                                * Math.pow(z, array[arrayLoop[3]]);

                        diff = Math.abs(constant - product) / constant;

                        if (diff < relativeError) {

                            relativeError = diff;
                            closestProduct = product;
                            Exponents = arrayLoop.clone();
                        }
                        arrayLoop[3]++;
                    }
                    arrayLoop[2]++;
                }
                arrayLoop[1]++;

            }
            arrayLoop[0]++;

        }
//assigning relativeErrorPercent and outprinting results
        double relativeErrorPercent = relativeError * 100;

        out.println("Relative error of approximation: " + relativeErrorPercent
                + "%");
        out.println("The approximation closest to your number is: "
                + closestProduct);
        out.print("The exponents that helped reach this approximation were: "
                + array[Exponents[0]] + " " + array[Exponents[1]] + " "
                + array[Exponents[2]] + " " + array[Exponents[3]]);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
