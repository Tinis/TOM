package no.uib.inf101.tom.model.screen;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.interactions.Interaction;

public class Button extends Interaction{

    private ButtonListener listener;
    private String label;

    public Button(Coordinate pos, double width, double height, ButtonListener listener, String label) {
        super(pos, width, height);
        this.listener = listener;
        this.label = label;
    }

    public Button(Coordinate pos, ButtonListener listener, String label) {
        this(pos, Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT, listener, label);
    }

    @Override
    protected void wasClicked() {
        this.listener.executeConsequence();
    }

    @Override
    public String getName() {
        return this.label;
    }

}
