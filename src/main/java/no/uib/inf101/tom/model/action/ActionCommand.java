package no.uib.inf101.tom.model.action;

import no.uib.inf101.tom.model.Coordinate;

/**
 * A command that tells a character what to do. 
 * It contains an action (as an action object) which is the action that is to be performed,
 *  and a pointer (as a coordinate) that tells which direction to perform the action towards.
 * 
 * Any actionCommand can be boiled down to these two values. 
 * Meaning anytime a character is told to do something its always some action in some direction. 
 */
public record ActionCommand(Action action, Coordinate pointer) {

}
