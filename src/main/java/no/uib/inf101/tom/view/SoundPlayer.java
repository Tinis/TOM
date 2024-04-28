package no.uib.inf101.tom.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SoundPlayer {
    private ArrayList<String> playingSounds;
    private HashMap<String, Sound> sounds;
    
    public SoundPlayer() {
        this.playingSounds = new ArrayList<>();
        this.sounds = new HashMap<>();
        File directory = new File("src/main/resources/sounds");
        for (String fileName : directory.list()) {
            int dotIndex = fileName.indexOf(".");
            String fileNameWithoutType = fileName.substring(0, dotIndex);
            Sound sound = new Sound(fileNameWithoutType);
            this.sounds.put(fileNameWithoutType, sound);
        }
        System.out.println("loaded sounds: " + this.sounds.keySet().toString());
    }

    /**
     * syncs up the players sounds with the sound players sounds. 
     * Meaning any sound that is in the input list will start playing or keep playing, 
     * and any sound that is playing and not in the input list will stop playing. 
     * This method will not make any sound loop. 
     * @param sounds a list of the sounds that are to play. 
     */
    public void playAndKeepSounds(ArrayList<String> sounds) {
        for (String sound : sounds) {
            if (!this.playingSounds.contains(sound)) {
                playSound(sound);
            }
        }
        ArrayList<String> toBeRemoved = new ArrayList<>(); 
        //having this list^ avoids a ConcurrentModificationException.
        for (String playingSound : this.playingSounds) {
            if (!sounds.contains(playingSound)) {
                toBeRemoved.add(playingSound);
            }
        }
        for (String sound : toBeRemoved) {
            stopSound(sound);
        }
    }

    /**
     * plays a sound. (won't have any effect if the sound is already playing)
     * @param soundName the name of the sound (without filetype (which btw has to be .wav))
     */
    public void playSound(String soundName) {
        this.playingSounds.add(soundName);
        System.out.println("playing sound: " + soundName);
        this.sounds.get(soundName).play();
        if (soundName.equals("nightclub") || soundName.equals("nightclubdream")) {
            this.sounds.get(soundName).loop();
        }
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
        this.playingSounds.remove(soundName);
        System.out.println("stoppping sound: " + soundName);
        this.sounds.get(soundName).stop();
    }
}
