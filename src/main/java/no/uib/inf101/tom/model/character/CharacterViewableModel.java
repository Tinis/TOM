package no.uib.inf101.tom.model.character;

import java.util.ArrayList;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.CollisionBox;

/**
 * this method encapsulates the model so that the characters can only view the relevant parts. 
 */
public interface CharacterViewableModel {

    /**
     * 
     * @return the coordinate of the player.
     */
    public Coordinate getPlayerCenter();

    /**
     * 
     * @return the collisionBoxes as collisionbox objects 
     * (so that characters can check if they overlap when they move and adjust accordingly)
     */
    public ArrayList<CollisionBox> getCollisionBoxesForCharacter();
}
