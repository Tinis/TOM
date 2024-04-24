package no.uib.inf101.tom.model.cutscene;

import java.io.File;

import no.uib.inf101.tom.Config;

/**
 * cutscenes are series of images
 * (one cutscene may only have one constant framerate). 
 * They work in much the same way as an action 
 * and are organized in much the same way as a level/screen 
 */
public class Cutscene {
    private String name;
    private String songName;

    private int stateDuration;
    private int frameCount;
    private int currentState;
    private int stateAmount; 

    private CutsceneConsequence consequence;

    public Cutscene(String name, int stateDuration, CutsceneConsequence consequence, String songName) {
        this.name = name;
        this.songName = songName;

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
                int underscoreIndex = filename.indexOf("_");
                String cutsceneName = filename.substring(0, underscoreIndex);
                if (cutsceneName.equals(this.name)) {
                    this.stateAmount ++;
                } else {
                }
            }
        }
        String debugString = String.format("loaded cutscene %s with states: %s", 
        this.name, this.stateAmount);
        System.out.println(debugString);
    }

    public Cutscene(String name, CutsceneConsequence consequence, String songName) {
        this(name, Config.STANDARD_CUTSCENE_STATE_DURATION, consequence, songName);
    }

    public Cutscene(String name, CutsceneConsequence consequence) {
        this(name, Config.STANDARD_CUTSCENE_STATE_DURATION, consequence, null);
    }

    public String getImagename() {
        return String.format("%s_%s", this.name, this.currentState+1);
    }

    public String getSongName() {
        return this.songName;
    }

    /**
     * updates the frame count of the cutscene. 
     * This does not make the cutscene go on to the next actual cutscene frame. 
     * Cutscenes will count frames 60 times per second (the games framerate)
     *  but the cutscene will probably not be 60 times per second. 
     * The cutscene therefore counts frames to know when to change to the next frame 
     * (cutsceneframes are known within the class as states).
     */
    public void updateFrameCount() {
        if (this.currentState >= this.stateAmount) {
            end();
            return;
        } 
        if (this.frameCount >= this.stateDuration) {
            this.currentState ++;
            this.frameCount = 0;
        }
        this.frameCount ++;
        
    }

    /**
     * ends the cutscene.
     */
    private void end() {
        this.consequence.executeConsequence();
        this.currentState = 0;
        this.frameCount = 0;
    }

    /**
     * skips the cutscene
     */
    public void skip() {
        end();
    }

}
