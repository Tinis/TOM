package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
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
        this.speed = Config.STANDARD_SPEED;
        this.movement = new PlaneVector(0,0);
    }

    @Override
    public PlaneVector getMovementVector() {
        return this.movement;
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
        //TODO: action can not override action that is not overridable (in its field).
        //and it can not override itself.
        this.currentAction = action;
    }

    public void updateCharacter() {
        //TODO: this must take a map as an argument to check for collission or something.
        if (this.currentAction != null) {
            this.currentAction.updateActionState();
        }
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
