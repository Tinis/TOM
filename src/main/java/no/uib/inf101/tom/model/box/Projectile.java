package no.uib.inf101.tom.model.box;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.ActionableModel;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.PlaneVector;

public class Projectile extends HitBox{
    private String name;
    private boolean actorIsGood;

    private int currentState;
    private int stateAmount;
    private int stateCounter; //the total amount of states that the projectile has had. 
    private int lifeSpanInStates; //the amount of states the projectile has before dying. a projectile may only last so long (it can't fly around forever)
    private int currentFrame; //the frames counted during this state so far. 
    private int framesPerState; //the amount of framse in one state.
    
    private Coordinate pos;
    private PlaneVector movement;
    private double speed;
    private int strength;

    private ActionableModel model;

    public Projectile(String name, boolean actorIsGood, int stateAmount, int lifeSpanInStates,
    int framesPerState, Coordinate pos, PlaneVector movement, double speed, 
    int strength, ActionableModel model) {
        super(pos, Config.PROJECTILE_WIDTH, Config.PROJECTILE_HEIGHT);
        this.name = name;
        this.actorIsGood = actorIsGood;

        this.currentState = 0;
        this.stateAmount = stateAmount;
        this.stateCounter = 0;
        this.lifeSpanInStates = lifeSpanInStates;
        this.currentFrame = 0;
        this.framesPerState = framesPerState;

        this.pos = pos;
        this.movement = movement;
        this.speed = speed;
        this.movement.setLength(speed);
        this.strength = strength;

        this.model = model;
    }

    /**
     * updates the projectile (moves it along the movementvector)
     * and updates the state.
     * This method also periodically calls model.hitcharactersinbox.
     * @return true if the projectile is to be removed from the model (its statecounter is more than its lifespan)
     * false if its not to be removed from the model. 
     */
    public boolean updateProjectile() {
        //updates the position
        this.pos = PlaneVector.coordMoved(this.pos, movement);
        setPos(this.pos);
        //updates the state
        this.currentFrame++;
        if (this.currentFrame >= this.framesPerState) {
            this.currentFrame = 0;
            //tries to hit characters in the model everytime it changes states. 
            this.model.hitCharactersInBox(this, this.actorIsGood, this.strength);
            this.currentState ++;
            this.stateCounter ++;
            if (this.currentState >= this.stateAmount) {
                //maybe have the model as a field and deal the hit everytime this updates. 
                this.currentState = 0;
            }
        }
        return (this.stateCounter > this.lifeSpanInStates);
    }

    public String getName() {
        return this.name;
    }

    public int getCurrentState() {
        return this.currentState;
    }

    
}
