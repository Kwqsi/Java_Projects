import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Kwasi Fosu
 *
 */
public final class ABCDguesser2 {

    /**
     * takes 4 variables and multiplies them together
     */
    private static double getProduct(double w, double x, double y, double z) {

        double product = w * x * y * z;
        return product;

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

        int[] arrayLoop = { 0, 0, 0, 0 };
        int[] Exponents = new int[4];
//big for loop
        for (arrayLoop[0] = 0; arrayLoop[0] < arrayLength; arrayLoop[0]++) {
//initializing variables that exist in the loop
            double diff = relativeError;
            double product = constant;
//smaller nested loops
            for (arrayLoop[1] = 0; arrayLoop[1] < arrayLength; arrayLoop[1]++) {
                for (arrayLoop[2] = 0; arrayLoop[2] < arrayLength; arrayLoop[2]++) {
                    for (arrayLoop[3] = 0; arrayLoop[3] < arrayLength; arrayLoop[3]++) {
//raising the variables to exponents in the smallest section of the loop
                        double w1 = Math.pow(w, array[arrayLoop[0]]);
                        double x1 = Math.pow(x, array[arrayLoop[1]]);
                        double y1 = Math.pow(y, array[arrayLoop[2]]);
                        double z1 = Math.pow(z, array[arrayLoop[3]]);

                        product = getProduct(w1, x1, y1, z1);
                        diff = Math.abs(constant - product) / constant;

                        if (diff < relativeError) {
                            //relative error is set to the absolute valued difference
                            relativeError = diff;
                            //copies loop and clones all current exponents inside to a different array
                            Exponents = arrayLoop.clone();
                            //closest approximation becomes current product
                            closestProduct = product;

                        }

                    }

                }
            }

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
