package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.character.ai.AICommander;
import no.uib.inf101.tom.model.character.ai.NullAI;

public class NPC extends Character{
    AICommander ai;

    public NPC(Coordinate pos, AICommander ai, boolean isGood) {
        super(pos, Config.STANDARD_CHARACTER_WIDTH, Config.STANDARD_CHARACTER_HEIGHT);
        this.name = "Some NPC";
        this.ai = ai;
        this.good = isGood;
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
    public void updateCharacter() {
        
        updateAction();
    }
    
}
