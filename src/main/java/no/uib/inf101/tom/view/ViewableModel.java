package no.uib.inf101.tom.view;

import no.uib.inf101.tom.model.CoordinatePointConverter;

public interface ViewableModel {

    /**
     * 
     * @return the coordinatePointConverter from the model.
     */
    public CoordinatePointConverter getCoordinateConverter();
}
