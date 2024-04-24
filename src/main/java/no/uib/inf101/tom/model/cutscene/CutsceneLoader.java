package no.uib.inf101.tom.model.cutscene;

import java.util.HashMap;

import no.uib.inf101.tom.model.TomModel;

public class CutsceneLoader {
    private HashMap<String, Cutscene> cutscenes;
    private TomModel model;

    public CutsceneLoader(TomModel model) {
        this.cutscenes = new HashMap<>();
        this.model = model;
        this.cutscenes.put("intro", intro());
    }

    public Cutscene getCutscene(String name) {
        return this.cutscenes.get(name);
    }

    //CUTSCENES
    private Cutscene intro() {
        Cutscene intro = new Cutscene("intro", this::loadBedroom1, "luciddream");
        return intro;
    }

    //CONSEQUENCES
    private void loadBedroom1() {
        this.model.loadLevel("bedroom1", 0);
    }
}
