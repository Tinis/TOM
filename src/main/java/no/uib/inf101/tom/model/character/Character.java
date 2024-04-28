package no.uib.inf101.tom.model.character;


import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.ActionableModel;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.Direction;
import no.uib.inf101.tom.model.PlaneVector;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.ActionableCharacter;
import no.uib.inf101.tom.model.action.Idle;
import no.uib.inf101.tom.model.action.ViewableAction;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.box.CharacterBox;
import no.uib.inf101.tom.model.box.CollisionBox;
import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.Projectile;
import no.uib.inf101.tom.model.box.ViewableBox;


public abstract class Character extends CharacterBox implements ViewableCharacter, ActionableCharacter{
    protected String name;

    protected int health;
    protected int maxHealth;
    protected int strength;
    protected int projetileStrength;
    protected int projectileLifeSpan;
    protected double reach;
    protected boolean targetable;
    protected boolean good;
    protected boolean canFire;

    protected double speed;
    protected PlaneVector movement;
    protected Coordinate destination;

    protected Direction facing;

    protected Action currentAction;
    protected ActionableModel model;

    public Character(Coordinate pos, double width, double height) {
        super(pos, width, height);
        
        //a standard chracter would have these stats
        this.maxHealth = Config.STANDARD_MAX_HEALTH;
        this.health = this.maxHealth;
        this.strength = Config.STANDARD_STRENGTH;
        this.projetileStrength = Config.STANDARD_PROJECTILE_STRENGTH;
        this.projectileLifeSpan = Config.STANDARD_PROJECTILE_LIFESPAN;
        this.speed = Config.STANDARD_SPEED;
        this.facing = Config.STANDARD_DIRECTION;
        this.reach = Config.STANDARD_PUNCH_REACH;
        this.canFire = true; //should maybe be false until after the first bossfight
        //and these properties
        this.targetable = true;
        this.good = true;
        this.currentAction = new Idle();
        this.movement = new PlaneVector(0,0);
        this.destination = pos;
    }

    public Character(Coordinate pos) {
        this(pos, Config.STANDARD_CHARACTER_WIDTH, Config.STANDARD_CHARACTER_HEIGHT);
    }

/////////////////////
//Setters and getters
/////////////////////
    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public void setHealthToFull() {
        this.health = this.maxHealth;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }

    public void setFacing(Direction direction) {
        this.facing = direction;
    }

    @Override
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    @Override
    public double getSpeed() {
        return this.speed;
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

    @Override
    public String getGoodOrBad() {
        if (this.good) {
            return "Good";
        } else {
            return "Bad";
        }
    }

    @Override
    public String getStatsString() {
        return String.format(
            "hp: %s, maxHp: %s, strength: %s, reach: %.3f, targetable: %s, speed: %.3f",
            this.health, this.maxHealth, this.strength, this.reach, this.targetable, this.speed
        );
    }

    @Override
    public void setDestination(Coordinate destination) {
        this.destination = destination;
        PlaneVector newMovement = new PlaneVector(this.pos, destination);
        this.movement = newMovement;
    }

    @Override
    public void scaleSpeed(double scaler) {
        this.speed = this.speed * scaler;
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
    }

    public void updateAction(CharacterViewableModel viewModel) {
        if (this.currentAction != null) {
            this.currentAction.updateActionFrame();
            if (this.currentAction.isMovingAction()) {
                move(viewModel);
            }
        } 
    }

    /**
     * Updates the character. 
     * @param model the model as a viewable model. 
     * (because the npcs need to see the model to know what to do)
     */
    public void updateCharacter(CharacterViewableModel viewModel) {
        updateAction(viewModel);
    }

    private void move(CharacterViewableModel viewModel) {
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
        //Checks for collision
        Coordinate oldPos = this.pos;
        this.pos = endCoord;
        for (CollisionBox box : viewModel.getCollisionBoxesForCharacter()) {
            if (box.overlapsWith(this)) {
                this.pos = oldPos;
            }
        }
        //stop if pos is almost at destination
        if (this.pos.isAlmostEqualTo(this.destination)){
            this.movement = new PlaneVector(0, 0);
            this.currentAction.stop();
        } 
    }

    @Override
    public void dealHit(HitBox hit) {
        this.model.hitCharactersInBox(hit, this.good, this.strength);
    }

    @Override 
    public void fireProjectile(Coordinate pointer) {
        if (this.canFire) {
            Projectile projectile = new Projectile("projectile1", this.good,
                Config.PROJECTILE_STATE_AMOUNT, this.projectileLifeSpan, Config.PROJECTILE_FRAMES_PER_STATE, this.pos,
                new PlaneVector(this.pos, pointer), Config.STANDARD_PROJECTILE_SPEED,
                this.projetileStrength, this.model);
            this.model.addProjectile(projectile);
        }
    }

    /**
     * makes the character take a hit
     * @param actorIsGood
     * @param strength
     * @return true if this character dies, false if they don't. 
     * (might change later with a dying action to have that animation).
     * (Could make this return true when the dying action is finished).
     */
    public boolean takeHit(boolean actorIsGood, int strength) {
        if (actorIsGood != this.good) {
            this.health -= strength;
        }
        return this.health <= 0;
    }

    /**
     * sets the model that the character will change with some actions. 
     * @param model the model as an ActionableModel. 
     */
    public void setModel(ActionableModel model) {
        this.model = model;
    }

    

    
}
