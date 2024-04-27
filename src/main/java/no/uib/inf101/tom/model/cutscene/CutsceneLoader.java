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
        this.cutscenes.put("objective2", objective2());
        this.cutscenes.put("nightclubenter", nightclubenter());
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
        //300 states means 5 seconds: 300f / 60fps = 5s
        return objective1;
    }

    private Cutscene objective2() {
        Cutscene objective2 = new Cutscene("objective2", 
            300, this::loadCity2, "carstart");
        return objective2;
    }

    private Cutscene nightclubenter() {
        Cutscene nightclubenter = new Cutscene("nightclubenter", this::loadNightClub1, "nightclubenter");
        return nightclubenter;
    }

    //CONSEQUENCES
    private void loadBedroom1() {
        this.model.loadLevel("bedroom1", 0);
    }

    private void loadObjective1() {
        this.model.loadCutscene("objective1");
    }

    private void loadCity2() {
        this.model.loadLevel("city2", 1);
    }

    private void loadNightClub1() {
        this.model.loadLevel("nightclub1", 1);
    }
}
