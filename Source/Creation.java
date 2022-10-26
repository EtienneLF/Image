package Source;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Creation {
    public static void imageCreation(String path, ArrayList<Dictionary<String, Integer>> imageDictionary,
            ArrayList<Dictionary<String, Integer>> paletteDictionary, String imagePath) {
        Logger logger = Logger.getLogger("Creation");
        logger.info("Starting creation of image : " + path);
        // Create Image
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        ArrayList<Dictionary<String, Integer>> pixelDictionary = createPixelDictionnary(imageDictionary,
                paletteDictionary);

        setPixel(result, pixelDictionary);

        imageC(path, result);
    }

    public static ArrayList<Dictionary<String, Integer>> createPixelDictionnary(
            ArrayList<Dictionary<String, Integer>> imageDictionary,
            ArrayList<Dictionary<String, Integer>> paletteDictionary) {
        ArrayList<Dictionary<String, Integer>> pixelDictionary = new ArrayList<>();
        for (int i = 0; i < imageDictionary.size(); i++) {
            Dictionary<String, Integer> pixel = imageDictionary.get(i);
            int min = 1000000;
            int index = 0;
            for (int j = 0; j < paletteDictionary.size(); j++) {
                Dictionary<String, Integer> palette = paletteDictionary.get(j);
                int distance = distance(pixel, palette);
                if (distance < min) {
                    min = distance;
                    index = j;
                }
            }
            Dictionary<String, Integer> temp = new Hashtable<>();
            temp.put("red", paletteDictionary.get(index).get("red"));
            temp.put("green", paletteDictionary.get(index).get("green"));
            temp.put("blue", paletteDictionary.get(index).get("blue"));
            pixelDictionary.add(temp);
        }
        return pixelDictionary;
    }

    public static int distance(Dictionary<String, Integer> pixel, Dictionary<String, Integer> palette) {
        int red = pixel.get("red") - palette.get("red");
        int green = pixel.get("green") - palette.get("green");
        int blue = pixel.get("blue") - palette.get("blue");
        int yellow = pixel.get("yellow") - palette.get("yellow");
        int purple = pixel.get("purple") - palette.get("purple");
        int cyan = pixel.get("cyan") - palette.get("cyan");
        int white = pixel.get("white") - palette.get("white");
        int black = pixel.get("black") - palette.get("black");
        return red * red + green * green + blue * blue + yellow * yellow + purple * purple + cyan * cyan + white * white
                + black * black;
    }

    public static void setPixel(BufferedImage result, ArrayList<Dictionary<String, Integer>> pixelDictionary) {
        int i = 0;
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                int red = pixelDictionary.get(i).get("red");
                int green = pixelDictionary.get(i).get("green");
                int blue = pixelDictionary.get(i++).get("blue");
                int rgb = (red << 16) | (green << 8) | blue;
                result.setRGB(x, y, rgb);
            }
        }
    }

    public static void imageC(String path, BufferedImage image) {
        // new Image
        File file = new File(path + ".png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
    }
}
