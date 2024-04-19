package no.uib.inf101.tom.model.screen;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.interactions.Interaction;

public class Button extends Interaction{

    private ButtonConsequence consequence;
    private String label;

    public Button(Coordinate pos, double width, double height, ButtonConsequence consequence, String label) {
        super(pos, width, height);
        this.consequence = consequence;
        this.label = label;
    }

    @Override
    protected void wasClicked() {
        this.consequence.executeConsequence();
    }
    

}
