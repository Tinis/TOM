package no.uib.inf101.tom.view;

import no.uib.inf101.tom.model.CoordinatePointConverter;
import no.uib.inf101.tom.model.character.ViewableCharacter;

public interface ViewableModel {

    /**
     * 
     * @return the coordinatePointConverter from the model.
     */
    public CoordinatePointConverter getCoordinateConverter();

    public ViewableCharacter getPlayer();
    //might replace with ArrayList<ViewableCharacter> getCharacters() later;

    public boolean isDebugging();
}
