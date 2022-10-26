package Source;

import java.util.ArrayList;
import java.util.Dictionary;

public class Image {
    private String imagePath;
    private String folderPath;
    private String resultPath;

    public Image(String imagePath, String folderPath, String resultPath) {
        this.imagePath = imagePath;
        this.folderPath = folderPath;
        this.resultPath = resultPath;
    }

    public void createImage() {
        // Creation palette de toute les images
        ArrayList<Dictionary<String, Integer>> paletteDictionary = Palette.Analyse(folderPath);
        //System.out.println(paletteDictionary);

        // Analyse de l'image
        ArrayList<Dictionary<String, Integer>> imageDictionary = Analyse.analysePixel(imagePath);
        //System.out.println(imageDictionary);

        // Creation de l'image finale
        Creation.imageCreation(resultPath, imageDictionary, paletteDictionary, imagePath);
    }
}
