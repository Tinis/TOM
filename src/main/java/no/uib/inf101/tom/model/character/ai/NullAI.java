package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.view.ViewableModel;

/**
 * An AI that only sends null as its actioncommand.
 */
public class NullAI implements AICommander{

    @Override
    public ActionCommand getActionCommand(ViewableModel model) {
        return null;
    }

    @Override
    public String getCommanderName() {
        return "nullai";
    }

    
}
