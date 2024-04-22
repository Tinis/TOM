package no.uib.inf101.tom.model.box;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;

public class Wall extends CollisionBox{

    public Wall(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }

    /**
     * creates a wall from a coord to a coord. A wall is a rectangular colissionbox, 
     * meaning the two coord-parameters have to be on a line (the line they form can't be diagonal). 
     * @param from
     * @param to
     */
    public Wall(Coordinate from, Coordinate to) {
        this(new Coordinate(0, 0), 0, 0); //dummy constructorcall, overse dette
        //finds the middle point
        double distance = PlaneVector.distanceBetweenTwoCoords(from, to);
        PlaneVector vector = new PlaneVector(from, to);
        vector.setLength(distance/2);
        Coordinate middle = PlaneVector.coordMoved(from, vector);
        //finds the width and height
        double xDiff = Math.abs(from.x() - to.x());
        double yDiff = Math.abs(from.y() - to.y());

        this.pos = middle;
        this.width = xDiff + Config.WALL_THICKNESS;
        this.height = yDiff + Config.WALL_THICKNESS;
    }

}
