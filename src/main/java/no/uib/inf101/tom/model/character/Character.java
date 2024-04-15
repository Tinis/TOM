package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.Direction;
import no.uib.inf101.tom.model.PlaneVector;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Idle;
import no.uib.inf101.tom.model.action.Punch;
import no.uib.inf101.tom.model.action.ViewableAction;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.CharacterBox;
import no.uib.inf101.tom.model.box.ViewableBox;


public abstract class Character extends CharacterBox implements ViewableCharacter, ActionableCharacter{
    protected String name;

    protected int health;
    protected int maxHealth;
    protected int strength;
    protected double reach;

    protected double speed;
    protected PlaneVector movement;
    protected Coordinate destination;

    protected Direction facing;

    protected Action currentAction;


    public Character(Coordinate pos, double width, double height) {
        super(pos, width, height);

        this.speed = Config.STANDARD_SPEED;
        this.facing = Config.STANDARD_DIRECTION;
        this.movement = new PlaneVector(0,0);
        this.destination = pos;

        this.currentAction = new Idle();
        this.reach = Config.STANDARD_PUNCH_REACH;
    }

    public Character(Coordinate pos) {
        this(pos, Config.STANDARD_CHARACTER_WIDTH, Config.STANDARD_CHARACTER_HEIGHT);
    }

/////////////////////
//Setters and getters
/////////////////////

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }

    public void setFacing(Direction direction) {
        this.facing = direction;
    }

    @Override
    public String getViewableMovementVector() {
        String movementData = String.format(
                "[%.3f, %.3f]", this.movement.getX(), this.movement.getY());
        return movementData;
    }

    @Override
    public ViewableBox getBox() {
        return this;
    }

    @Override 
    public Direction getFacing() {
        return this.facing;
    }

    @Override
    public ViewableAction getViewableAction() {
        return this.currentAction;
    }

    @Override
    public Coordinate getPos() {
        return this.getBox().getCenter();
    }

    @Override
    public double getReach() {
        return this.reach;
    }

    public void setDestination(Coordinate coord) {
        this.destination = coord;
        PlaneVector newMovement = new PlaneVector(this.pos, coord);
        this.movement = newMovement;
    }

////////////////
//Action-related
////////////////

    private void faceToward(Coordinate coord) {
        PlaneVector facingVector = new PlaneVector(this.pos, coord);
        double xFacing = facingVector.getX();
        double yFacing = facingVector.getY();
        if (Math.abs(xFacing) > Math.abs(yFacing)) {
            if (xFacing > 0) {
                this.facing = Direction.EAST;
            } else {
                this.facing = Direction.WEST;
            }
        } else {
            if (yFacing > 0) {
                this.facing = Direction.SOUTH;
            } else {
                this.facing = Direction.NORTH;
            }
        }
    }

    public void sendActionCommand(ActionCommand actionCommand) {
        if (actionCommand == null) {
            return;
        }
        Action action = actionCommand.action();
        Coordinate pointer = actionCommand.pointer();
        action.setPointer(pointer);
        if (this.currentAction.isOverrideable()) {
            faceToward(pointer);
        }
        if (action instanceof Walk) {
            setDestination(pointer);
        }
        startAction(action);
    }

    
    @Override
    public boolean startAction(Action action) {
        if (this.currentAction.isOverrideable()) {
            if (!this.currentAction.isSameActionAs(action)) {
                this.currentAction = action;
                this.currentAction.setActingCharacter(this);
                return true;
            }
        }
        return false;
    }

    @Override
    public void overrideAction(Action action) {
        this.currentAction = action;
        this.currentAction.setActingCharacter(this);
        if (action instanceof Walk) {
            faceToward(this.destination);
        }
    }

    public void updateAction() {
        //TODO: this must take a map as an argument to check for collission or something. this is called from the model. 
        if (this.currentAction != null) {
            this.currentAction.updateActionFrame();
            if (this.currentAction instanceof Walk) {
                move();
            }
        } 
    }

    public void updateCharacter() {
        updateAction();
    }

    private void move() {
        //if the distance between the character and the destination is less than the speed
        //set the length to the distance.
        double disctanceToDestination = PlaneVector.distanceBetweenTwoCoords(
            this.pos, this.destination);
        if (disctanceToDestination < this.speed) {
            this.movement.setLength(disctanceToDestination);
        } else {
            this.movement.setLength(this.speed);
        }
        Coordinate endCoord = PlaneVector.coordMoved(this.pos, this.movement);
        //TODO: check for collission. do something smart if there is a collission. 
        this.pos = endCoord;
        if (this.pos.isAlmostEqualTo(this.destination)){
            this.movement = new PlaneVector(0, 0);
            this.currentAction.stop();
        } 
    }    
}
