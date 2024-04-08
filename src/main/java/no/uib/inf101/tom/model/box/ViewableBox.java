package no.uib.inf101.tom.model.box;

import java.awt.Shape;

public interface ViewableBox {

    /**
     * Returns the shape of the box. Meaning a java.awt.Shape object
     * @return the shape corresponsing to the given box. 
     */
    public Shape getShape();
}
