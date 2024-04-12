package no.uib.inf101.tom.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ImageFinder {
    private HashMap<String, BufferedImage> images;

    public ImageFinder() {
        this.images = new HashMap<>();

        File directory = new File("src/main/resources/images");
        for (String fileName : directory.list()) {
            BufferedImage image = Inf101Graphics.loadImageFromResources("/images/" + fileName);
            int dotIndex = fileName.indexOf(".");
            String fileNameWithoutType = fileName.substring(0, dotIndex);
            images.put(fileNameWithoutType, image);
        }
    }


    public BufferedImage findImage(String name) {
        return this.images.get(name);
    }
}
