package no.uib.inf101.tom.model.box;

import no.uib.inf101.tom.model.Coordinate;

public class CollisionBox extends RectangularBox{

    public CollisionBox(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }

    @Override
    public String getName() {
        return "undefined collisionbox";
    }

}
