package no.uib.inf101.tom.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import no.uib.inf101.tom.model.screen.Screen;

public class ImageFinder {
    private HashMap<String, BufferedImage> levelImages;
    private HashMap<String, BufferedImage> screenImages;

    public ImageFinder() {
        //Levels
        this.levelImages = new HashMap<>();
        File directory = new File("src/main/resources/levels");
        loadHashMapFromDirectory(this.levelImages, directory);
        //Screens
        this.screenImages = new HashMap<>();
        directory = new File("src/main/resources/screens");
        loadHashMapFromDirectory(this.screenImages, directory);
    }

    private void loadHashMapFromDirectory(HashMap<String, BufferedImage> hashMap, File directory) {
        for (String fileName : directory.list()) {
            String directoryName = String.format("/%s/", directory.getName());
            BufferedImage image = Inf101Graphics.loadImageFromResources(directoryName + fileName);
            int dotIndex = fileName.indexOf(".");
            String fileNameWithoutType = fileName.substring(0, dotIndex);
            hashMap.put(fileNameWithoutType, image);
        }
    }


    //Finding methods
    public BufferedImage findLevel(String levelName) {
        return this.levelImages.get(levelName);
    }

    /**
     * 
     * @param screenName the name of the screen (also the name of the )
     * @return
     */
    public BufferedImage findScreen(Screen screen) {
        String screenName = screen.getName();
        return this.screenImages.get(screenName);
    }
}
