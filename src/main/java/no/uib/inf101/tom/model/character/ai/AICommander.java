package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.character.CharacterViewableModel;

/**
 * An AICommander that is updated once every frame. 
 * Remember when making an AI to set state to 0 in constructor. 
 * When deciding amount of states remember that 60 states = 60 frames = 1 second.
 */
public abstract class AICommander {

    protected int stateAmount;
    protected int state;


    /**
     * gets the next actioncommand based on how the current model looks. 
     * This method updates the state of the ai (all AI's work in a loop with states
     *  and they send a specific command at each state).
     * @param model the model containing the info that the commander needs to do the command. 
     * @return the next actioncommand. 
     */
    public ActionCommand getActionCommand(CharacterViewableModel model) {
        this.state += 1;
        if (this.state == this.stateAmount) {
            this.state = 0;
        }
        return getActionCommandForState(model, this.state);
    }

    protected abstract ActionCommand getActionCommandForState(CharacterViewableModel model, int state);

    /**
     * 
     * @return the name of this AICommander.
     */
    public abstract String getCommanderName();
}
