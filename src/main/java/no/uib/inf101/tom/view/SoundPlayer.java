package no.uib.inf101.tom.view;

import java.io.File;
import java.util.HashMap;

public class SoundPlayer {
    private HashMap<String, Sound> sounds;
    
    public SoundPlayer() {
        this.sounds = new HashMap<>();
        File directory = new File("src/main/resources/sounds");
        for (String fileName : directory.list()) {
            int dotIndex = fileName.indexOf(".");
            String fileNameWithoutType = fileName.substring(0, dotIndex);
            Sound sound = new Sound(fileNameWithoutType);
            this.sounds.put(fileNameWithoutType, sound);
        }
    }

    /**
     * plays a sound. (won't have any effect if the sound is already playing)
     * @param soundName the name of the sound (without filetype (which btw has to be .wav))
     */
    public void playSound(String soundName) {
        this.sounds.get(soundName).play();
    }

    /**
     * loops a sound. This method must be called after the play method. 
     * @param soundName the name of the sound (without filetype (which btw has to be .wav))
     */
    public void loopSound(String soundName) {
        this.sounds.get(soundName).loop();
    }

    /**
     * stops a sound. Calling this when no sound is playing won't have any effect. 
     * @param soundName the name of the sound (without filetype (which btw has to be .wav))
     */
    public void stopSound(String soundName) {
        this.sounds.get(soundName).stop();
    }
}
