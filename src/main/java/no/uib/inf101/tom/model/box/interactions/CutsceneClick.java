package no.uib.inf101.tom.model.box.interactions;

import no.uib.inf101.tom.Config;
import no.uib.inf101.tom.model.Coordinate;

public class CutsceneClick extends Item{
    private String cutscene;

    public CutsceneClick(Coordinate pos, double width, double height, String cutscene) {
        super(pos, width, height);
        this.cutscene = cutscene;
    }

    public CutsceneClick(Coordinate pos, String cutscene) {
        this(pos, Config.DOOR_WIDTH, Config.DOOR_HEIGHT, cutscene);
    }

    @Override
    protected void wasClicked() {
        this.model.loadCutscene(this.cutscene);
    }

}
