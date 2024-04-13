package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.CharacterBox;
import no.uib.inf101.tom.model.box.ViewableBox;

public abstract class Character extends CharacterBox implements ViewableCharacter{
    protected String name;

    protected int health;
    protected int maxHealth;
    protected int strength;

    protected double speed;
    protected PlaneVector movement;
    protected Action currentAction;

    public Character(Coordinate pos, double width, double height) {
        super(pos, width, height);
    }

    @Override
    public ViewableBox getBox() {
        return this;
    }

    public void setDestination(Coordinate coord) {
        PlaneVector newMovement = new PlaneVector(this.pos, coord);
        this.movement = newMovement;
    }

    public void startAction(Action action) {
        this.currentAction = action;
    }

    public void updateCharacter() {
        //TODO: this must take a map as an argument to check for collission. 
        this.currentAction.updateActionState();
        if (this.currentAction instanceof Walk) {
            this.move();
        }
    }

    private void move() {
        this.movement.setLength(this.speed);
        Coordinate endCoord = PlaneVector.coordMoved(this.pos, this.movement);
        //TODO: check for collission. do something smart if there is a collission. 
        this.pos = endCoord;        
    }


    
}
