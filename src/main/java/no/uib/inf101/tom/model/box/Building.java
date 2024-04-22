package no.uib.inf101.tom.model.box;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;

/**
 * A building. Does not need to contain drawing info. 
 * (the reason i have a class for building and wall is to have more constructors with the same types). 
 * The pixelart drawing will be part of the levelImage. 
 */
public class Building extends CollisionBox{
    protected String name;

    public Building(Coordinate pos, double width, double height, String name) {
        super(pos, width, height);
        this.name = name;
    }

    public Building(Coordinate upLeft, Coordinate downRight, String name) {
        //java meiner at kallet til ein annan constructor må vere det første i denne contructoren
        this(new Coordinate(0, 0), 0, 0, name); //dummy constructor, overse dette
        //her setter eg dei faktiske fieldsne.
        this.width = downRight.x() - upLeft.x();
        this.height = downRight.y() - upLeft.y();
        this.pos = new Coordinate(upLeft.x() + this.width/2, upLeft.y() + this.height/2);
    }

    public Building(Coordinate upLeft, double width, double height) {
        this(upLeft, new Coordinate(upLeft.x() + width, upLeft.y() + height), "unnamed building");
    }
}
