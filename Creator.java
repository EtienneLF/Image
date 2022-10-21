

import java.util.logging.Logger;

import Source.Image;

public class Creator {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Creator");
        String folderPath = "Entry/Folder";
        String imagePath = "Entry/Test.png";
        String resultPath = "Exit/Result.png";

        System.out.println("--------------------------------------------------------------");

        if (args.length == 0) {
            logger.info("No arguments given, using default values");
        }
        logger.info("Starting with parameters:" + "\n" +
                "Folder path: " + folderPath + "\n" +
                "Image path: " + imagePath + "\n" +
                "Result path: " + resultPath + "\n");

        Image image = new Image(imagePath, folderPath, resultPath);
        image.createImage();

        logger.info("Finished");
        System.out.println("--------------------------------------------------------------");
    }
}