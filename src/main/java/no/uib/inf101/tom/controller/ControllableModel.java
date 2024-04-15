package no.uib.inf101.tom.controller;

import java.awt.geom.Point2D;

import no.uib.inf101.tom.model.action.Action;

public interface ControllableModel {

    /**
     * 
     * @param point the point that was clicked. 
     * The walking will be toward the coordinate that corresponds to this point. 
     */
    public void walk(Point2D point);

    /**
     * send an action to the model based on the keypress. 
     * For example, the Q key would send a punch action. 
     * @param action the action to send. 
     */
    public void sendAction(Action action);

    /**
     * Toggles the debugMode field in the model from true to false or false to true.
     */
    public void toggleDebug();


    /**
     * updates the mousePos field with the coordinate corresponding to the argument point. 
     * @param point the point where the mouse is at. 
     */
    public void mouseIsAt(Point2D point);
}
