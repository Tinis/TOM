package no.uib.inf101.tom.model.box.interactions;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.TomModel;
import no.uib.inf101.tom.model.box.RectangularBox;

/**
 * an interactable box (such as a door or a menubutton)
 */
public abstract class Interaction extends RectangularBox{

    protected TomModel model;

    public Interaction(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }

    public void setModel(TomModel model) {
        this.model = model;
    }

    /**
     * checks if the interaction was clicked. If it was it performs it's function 
     * (it's wasClicked method)
     */
    public void checkIfClicked(Coordinate click) {
        if (this.coordIsOnBox(click)) {
            this.wasClicked();
        }
    } 
    
    /**
     * this method is to be called when the interaction was clicked. 
     * Will behave differently for every interaction. 
     */
    protected abstract void wasClicked();
}
    