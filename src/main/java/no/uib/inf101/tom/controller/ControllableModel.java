package no.uib.inf101.tom.controller;

import java.awt.geom.Point2D;

public interface ControllableModel {

    /**
     * 
     * @param point the point that was clicked. 
     * The walking will be toward the coordinate that corresponds to this point. 
     */
    public void walk(Point2D point);
}
