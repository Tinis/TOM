package no.uib.inf101.tom.model.box;

import java.awt.Shape;

import no.uib.inf101.tom.model.Coordinate;

public interface ViewableBox {

    /**
     * Returns the shape of the box. Meaning a java.awt.Shape object
     * @return the shape corresponsing to the given box. 
     */
    public Shape getShape();

    /**
     * 
     * @return the name of the character or structure or interaction or hit or projectile 
     * that this box represents.
     */
    public String getName();

    /**
     * gets the center Coordinate of the box. 
     * @return the coordinate at the center of the box. 
     */
    public Coordinate getCenter();
}
