import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        NaturalNumber upperLimit = new NaturalNumber2(n);
        upperLimit.increment();
        //upper limit is n+1 as a tooHigh condition
        NaturalNumber one = n.newInstance();
        one.increment();
        NaturalNumber two = new NaturalNumber2(2);

        //setting natural number constants
        NaturalNumber lowEnough = new NaturalNumber2(0);

        NaturalNumber tooHigh = new NaturalNumber2(upperLimit);
//creating tooHigh and lowEnough barriers
        //subtract lowEnough from tooHigh to get initial difference
        tooHigh.subtract(lowEnough);

        NaturalNumber guess = new NaturalNumber2(0);

        while (tooHigh.compareTo(one) > 0) {
            //restore tooHigh
            tooHigh.add(lowEnough);
//finding midpoint with guess
            guess.copyFrom(lowEnough);
            guess.add(tooHigh);
            guess.divide(two);
//raising the midpoint to r
            NaturalNumber power = new NaturalNumber2(0);
            power.copyFrom(guess);
            power.power(r);
//comparing the result of the power and the upperLimit
            int powerN = power.compareTo(upperLimit);

            //if the power is less than upperLimit replace lowEnough with the guess
            if (powerN < 0) {
                lowEnough.copyFrom(guess);
            }
            //if the power is greater than upperLimit replace tooHigh with the guess

            else {
                tooHigh.copyFrom(guess);

            }
//subtract lowEnough from tooHigh to update the condition
            tooHigh.subtract(lowEnough);

        }
//copy lowEnough to n
        n.copyFrom(lowEnough);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}