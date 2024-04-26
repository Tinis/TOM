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
        this.cutscenes.put("objective1", objective1());
    }

    public Cutscene getCutscene(String name) {
        return this.cutscenes.get(name);
    }

    //CUTSCENES
    private Cutscene intro() {
        Cutscene intro = new Cutscene("intro", this::loadObjective1, "luciddream");
        return intro;
    }

    private Cutscene objective1() {
        Cutscene objective1 = new Cutscene("objective1", 
            300, this::loadBedroom1, null);
        return objective1;
    }

    //CONSEQUENCES
    private void loadBedroom1() {
        this.model.loadLevel("bedroom1", 0);
    }

    private void loadObjective1() {
        this.model.loadCutscene("objective1");
    }
}
