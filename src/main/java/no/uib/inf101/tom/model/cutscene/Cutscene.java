package no.uib.inf101.tom.model.cutscene;

import java.io.File;

import no.uib.inf101.tom.Config;

/**
 * cutscenes are series of images with varying framerate 
 * (one cutscene may only have one constant framerate). 
 * They work in much the same way as an action 
 * and are organized in much the same way as a level/screen 
 * and are triggered by a gamestate-change.
 */
public class Cutscene {
    private String name;
    private int stateDuration;
    private int frameCount;
    private int currentState;
    private int stateAmount; 
    private CutsceneConsequence consequence;
    //stateAmount automatically counts the amount of images with the name
    
    public Cutscene(String name, int stateDuration, CutsceneConsequence consequence) {
        this.name = name;
        this.consequence = consequence;
        this.frameCount = 0;
        this.stateDuration = stateDuration; //the duration of a state (in frames per state). 
        this.currentState = 0;
        this.stateAmount = 0;

        if (Config.DISABLE_CUTSCENES) {
            //don't change the stateAmount (this will skip the cutscene)
        } else {
            File directory = new File("src/main/resources/cutscenes");
            for (String filename : directory.list()) {
                int dotIndex = filename.indexOf(".");
                String cutsceneName = filename.substring(0, dotIndex - 1);
                if (cutsceneName.equals(this.name)) {
                    this.stateAmount ++;
                }
            }
        }
    }

    public Cutscene(String name, CutsceneConsequence consequence) {
        this(name, Config.STANDARD_CUTSCENE_STATE_DURATION, consequence);
    }

    public void updateFrameCount() {
        if (this.currentState >= this.stateAmount) {
            this.consequence.executeConsequence();
            this.currentState = 0;
            this.frameCount = 0;
            return;
        } 
        if (this.frameCount >= this.stateDuration) {
            this.currentState ++;
            this.frameCount = 0;
        }
        this.frameCount ++;
        
    }

}
