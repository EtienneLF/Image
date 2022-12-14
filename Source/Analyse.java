package Source;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.imageio.ImageIO;

public class Analyse {

    public static Dictionary<String, Integer> analyseImage(String path) {
        Logger logger = Logger.getLogger("Analyse");
        logger.info("Starting analyse of image : " + path);
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            logger.severe("Error while reading image : " + path);
            e.printStackTrace();
            return null;
        }

        int[][][] img = getTripleArray(image);

        return score(img);
    }

    public static ArrayList<Dictionary<String, Integer>> analysePixel(String path) {
        Logger logger = Logger.getLogger("Analyse");
        logger.info("Starting analyse of image : " + path);
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            logger.severe("Error while reading image : " + path);
            e.printStackTrace();
            return null;
        }

        int[][][] img = getTripleArray(image);

        return scorePixel(img);
    }

    public static int[][][] getTripleArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[][][] result = new int[width][height][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                // System.out.println("x: " + x + " y: " + y + " red: " + red + " green: " +
                // green + " blue: " + blue);
                result[x][y][0] = red;
                result[x][y][1] = green;
                result[x][y][2] = blue;
            }
        }

        return result;
    }

    public static Dictionary<String, Integer> score(int[][][] img) {
        // System.out.println("img : " + img[0][0][0] + " " + img[0][0][1] + " " +
        // img[0][0][2]);
        int[] mean = mean(img);

        System.out.println("Mean: " + mean[0] + " " + mean[1] + " " + mean[2]);

        Dictionary<String, Integer> result = new Hashtable<>();
        result.put("red", mean[0]);
        result.put("green", mean[1]);
        result.put("blue", mean[2]);
        result.put("yellow", mean[0] + mean[1]);
        result.put("purple", mean[0] + mean[2]);
        result.put("cyan", mean[1] + mean[2]);
        result.put("white", mean[0] + mean[1] + mean[2]);
        result.put("black", (255 - mean[0]) + (255 - mean[1]) + (255 - mean[2]));

        //System.out.println("Result Score : " + result);
        return result;
    }

    public static ArrayList<Dictionary<String, Integer>> scorePixel(int[][][] img) {
        // System.out.println("img : " + img[0][0][0] + " " + img[0][0][1] + " " +
        // img[0][0][2]);
        ArrayList<Dictionary<String, Integer>> result = new ArrayList<>();
        for (int y = 0; y < img[0].length; y++) {
            for (int x = 0; x < img.length; x++) {
                Dictionary<String, Integer> pixel = new Hashtable<>();
                pixel.put("red", img[x][y][0]);
                pixel.put("green", img[x][y][1]);
                pixel.put("blue", img[x][y][2]);
                pixel.put("yellow", img[x][y][0] + img[x][y][1]);
                pixel.put("purple", img[x][y][0] + img[x][y][2]);
                pixel.put("cyan", img[x][y][1] + img[x][y][2]);
                pixel.put("white", img[x][y][0] + img[x][y][1] + img[x][y][2]);
                pixel.put("black", (255 - img[x][y][0]) + (255 - img[x][y][1]) + (255 - img[x][y][2]));
                result.add(pixel);
            }
        }
        //System.out.println("Result Score : " + result);
        return result;
    }

    public static int[] mean(int[][][] img) {
        int width = img.length;
        int height = img[0].length;
        int[] result = { 0, 0, 0 };

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[0] += img[x][y][0];
                result[1] += img[x][y][1];
                result[2] += img[x][y][2];
            }
        }

        // System.out.println("Result Mean : " + result[0] + " " + result[1] + " " +
        // result[2]);

        result[0] = result[0] / (width * height);
        result[1] = result[1] / (width * height);
        result[2] = result[2] / (width * height);

        return result;
    }
}
