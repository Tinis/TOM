package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.box.HitBox;

public interface ActionableCharacter {

    /**
     * deal damage to characters in a hitbox (deal a hit) who have a different good-value
     * (good characters can only damage bad characters and vice versa).
     * @param hit the hitbox. 
     */
    public void dealHit(HitBox hit);

    /**
     * makes character fire a projectile towards the pointer. 
     * @param pointer the point at which to shoot. 
     */
    public void fireProjectile(Coordinate pointer);

    /**
     * multiplies the characters speed with the scaler. 
     * @param scaler the scaler that is two be multiplied with the speed to make the new speed. 
     */
    public void scaleSpeed(double scaler);


    /**
     * sets the destination of the character
     * @param destination
     */
    public void setDestination(Coordinate destination);


    /**
     * attempt to start a new action (it will not start if its current action is not overridable). 
     * @param action the new action that will be started. 
     * @return will return true if a new action was started. false if not. 
     */
    public boolean startAction(Action action);

    /**
     * starts a new action (will override any action).
     * @param action the new action that will be started. 
     */
    public void overrideAction(Action action);

    /**
     * 
     * @return the pos of the character. (this is used in calculating an actions effect). 
     */
    public Coordinate getPos();

    /**
     * 
     * @return the reach of the character.
     */
    public double getReach();
}
