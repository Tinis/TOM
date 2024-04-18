package no.uib.inf101.tom.model.box.interactions;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;

public abstract class Item extends Interaction{

    protected double range;

    public Item(Coordinate pos, double width, double height) {
        super(pos, width, height);
        this.range = Config.INTERACTION_RANGE;
    }

    @Override
    public void checkIfClicked(Coordinate click) {
        double playerDistance = PlaneVector.distanceBetweenTwoCoords(
            this.pos, this.model.getPlayerCenter());
        if (this.coordIsOnBox(click) && (playerDistance <= this.range)) {
            this.wasClicked();
        }
    } 

}
