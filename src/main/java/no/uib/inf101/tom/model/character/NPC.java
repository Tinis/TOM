package no.uib.inf101.tom.model.character;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;

public class NPC extends Character{

    public NPC(Coordinate pos) {
        super(pos, Config.STANDARD_CHARACTER_WIDTH, Config.STANDARD_CHARACTER_HEIGHT);
        this.name = "Some NPC";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void updateCharacter() {
        updateAction();
    }
    
}
