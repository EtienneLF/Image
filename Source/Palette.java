package Source;

import java.io.File;

public class Palette {

    public static void Analyse(String path) {
        // For all Images in folder
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                Analyse.analyseImage(file.getPath());
            }
        }
    }
}