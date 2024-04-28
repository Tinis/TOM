package no.uib.inf101.tom.model.cutscene;

import java.util.HashMap;

import no.uib.inf101.tom.model.TomModel;

/**
 * A class that contains the different cutscenes (the info for them not the images themselves). 
 */
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
        this.cutscenes.put("objective3", objective3());
        this.cutscenes.put("tominbed", tominbed());
        this.cutscenes.put("objective4", objective4());
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
        Cutscene nightclubenter = new Cutscene("nightclubenter", 
            this::loadNightClub1, "nightclubenter");
        return nightclubenter;
    }

    private Cutscene objective3() {
        Cutscene objective3 = new Cutscene("objective3", 
            300, this::loadTomInBed, null);
        return objective3;
    }

    private Cutscene tominbed() {
        Cutscene tominbed = new Cutscene("tominbed",
            this::loadObjective4, null);
        return tominbed;
    }

    private Cutscene objective4() {
        Cutscene objective4 = new Cutscene("objective4", 
            300, this::loadDream1, null);
        return objective4;
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

    private void loadTomInBed() {
        this.model.loadCutscene("tominbed");
    }

    private void loadObjective4() {
        this.model.loadCutscene("objective4");
    }

    private void loadDream1() {
        this.model.loadLevel("dream1", 1);
    }
}
