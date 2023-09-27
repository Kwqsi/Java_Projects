import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Kwasi Fosu
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        // TODO: fill in body

        boolean power = false;
        boolean root = false;
        boolean subtract = false;
        boolean divide = false;

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        if (!bottom.isZero()) {
            divide = true;
        }
        if (bottom.compareTo(top) <= 0) {
            subtract = true;
        }
        if (bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0) {
            root = true;
        }
        if (bottom.compareTo(INT_LIMIT) <= 0) {
            power = true;
        }

        view.updatePowerAllowed(power);
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
        view.updateSubtractAllowed(subtract);
        view.updateDivideAllowed(divide);
        view.updateRootAllowed(root);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.copyFrom(bottom);
        this.updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
//initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
//complete the calculation & clear top
        top.add(bottom);
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {

        // TODO: fill in body
        //initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
//complete the calculation & clear top
        top.subtract(bottom);
        bottom.transferFrom(top);
        //change model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {

        // TODO: fill in body
        //initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
//complete the calculation & clear top
        top.multiply(bottom);
        bottom.transferFrom(top);
        //change model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {

        // TODO: fill in body
        //initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber remainder = new NaturalNumber2(top.divide(bottom));
//complete the calculation & clear top
        bottom.transferFrom(top);
        top.transferFrom(remainder);

        //change model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {

        // TODO: fill in body
        //initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
//complete the calculation & clear top
        top.multiply(bottom);
        bottom.transferFrom(top);
        //change model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {

        // TODO: fill in body
        //initialize top bottom
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
//complete the calculation & clear top
        top.root(bottom.toInt());
        bottom.transferFrom(top);
        //change model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // TODO: fill in body
        this.model.bottom().multiplyBy10(digit);
//change model
        updateViewToMatchModel(this.model, this.view);
    }

}
