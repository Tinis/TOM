package no.uib.inf101.tom.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import no.uib.inf101.tom.model.screen.Screen;

public class ImageFinder {
    private HashMap<String, BufferedImage> levelImages;
    private HashMap<String, BufferedImage> screenImages;
    private HashMap<String, BufferedImage> otherImages;

    public ImageFinder() {
        //Levels
        this.levelImages = new HashMap<>();
        File directory = new File("src/main/resources/levels");
        loadHashMapFromDirectory(this.levelImages, directory);
        //Screens
        this.screenImages = new HashMap<>();
        directory = new File("src/main/resources/screens");
        loadHashMapFromDirectory(this.screenImages, directory);
        //Other
        this.otherImages = new HashMap<>();
        directory = new File("src/main/resources/other");
        loadHashMapFromDirectory(this.otherImages, directory);
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
    /**
     * 
     * @param levelName the name of the levelimage to find. (also the name of the file)
     * @return the image.
     */
    public BufferedImage findLevel(String levelName) {
        return this.levelImages.get(levelName);
    }

    /**
     * 
     * @param screen the screen. (the name of the screen is the name of the file). 
     * @return the image.
     */
    public BufferedImage findScreen(Screen screen) {
        String screenName = screen.getName();
        return this.screenImages.get(screenName);
    }

    /**
     * 
     * @param imageName the name of the image to find from the other folder.
     * @return the image. 
     */
    public BufferedImage findOther(String imageName) {
        return this.otherImages.get(imageName);
    }
}
