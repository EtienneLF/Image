package Source;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;

public class Palette {

    public static ArrayList<Dictionary<String, Integer>> Analyse(String path) {
        // For all Images in folder
        ArrayList<Dictionary<String, Integer>> result = new ArrayList<>();
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                result.add(Analyse.analyseImage(file.getPath()));
            }
        }
        return result;
    }
}