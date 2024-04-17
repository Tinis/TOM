package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.character.CharacterViewableModel;

/**
 * An AI that only sends null as its actioncommand.
 */
public class NullAI extends AICommander{

    @Override
    public ActionCommand getActionCommand(CharacterViewableModel model) {
        return null;
    }


    @Override
    public String getCommanderName() {
        return "nullai";
    }


    @Override
    protected ActionCommand getActionCommandForState(CharacterViewableModel model, int state) {
        // do nutin
        return null;
    }

    
}
