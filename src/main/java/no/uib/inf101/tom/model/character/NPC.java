package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.action.Action;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.character.ai.AICommander;
import no.uib.inf101.tom.model.character.ai.NullAI;
import no.uib.inf101.tom.view.ViewableModel;

public class NPC extends Character{
    AICommander ai;

    public NPC(Coordinate pos, AICommander ai, boolean isGood, String name) {
        super(pos, Config.STANDARD_CHARACTER_WIDTH, Config.STANDARD_CHARACTER_HEIGHT);
        this.name = name;
        this.ai = ai;
        this.good = isGood;
        this.name = name;
    }
    public NPC(Coordinate pos, AICommander ai, boolean isGood) {
        this(pos, ai, isGood, "Some NPC");
    }

    public NPC(Coordinate pos, boolean isGood) {
        this(pos, new NullAI(), isGood);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCommander() {
        return this.ai.getCommanderName();
    }

    @Override
    public void updateCharacter(ViewableModel viewModel) {
        updateAction();
        ActionCommand nextAction = this.ai.getActionCommand(viewModel);
        sendActionCommand(nextAction);
    }
    
}
