package no.uib.inf101.tom.model.character.ai;

import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.view.ViewableModel;

public interface AICommander {

    /**
     * gets the next actioncommand based on how the current model looks.
     * @param model the model containing the info that the commander needs to do the command. 
     * @return the next actioncommand. 
     */
    public ActionCommand getActionCommand(ViewableModel model);

    /**
     * 
     * @return the name of this AICommander.
     */
    public String getCommanderName();
}
