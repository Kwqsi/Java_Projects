/**
 * Model class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class DemoModel1 implements DemoModel {

    /**
     * Model variables.
     */
    private String input, output;

    /**
     * Default constructor.
     */
    public DemoModel1() {
        /*
         * Initialize model; both variables start as empty strings
         */
        this.input = "";
        this.output = "";
    }

    @Override
    public void setInput(String input) {
        //setting input
        this.input = input;
    }

    @Override
    public String input() {
        //making appropriate returns
        return this.input;
    }

    @Override
    public void setOutput(String output) {
        //setting output
        this.output = output;
    }

    @Override
    public String output() {
        //making appropriate returns

        return this.output;
    }

}
