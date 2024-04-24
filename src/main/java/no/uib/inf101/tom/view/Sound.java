package no.uib.inf101.tom.view;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    //Code inspired by RyiSnow - Sound - How to Make a 2D Game in Java #9
    //url: https://youtu.be/nUHh_J2Acy8?t=339
    private Clip clip;
    private String name;

    /**
     * all soundfiles have to be located in the "resources/sounds" folder.
     * all soundfiles have to be .wav files.
     * @param fileName the name of the sound file without the type (it has to be a .wav)
     */
    public Sound(String fileName) {
        this.name = fileName;
        String path = "/sounds/" + fileName + ".wav";
        URL soundUrl = getClass().getResource(path);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
