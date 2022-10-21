package Source;

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
        //Creation palette de toute les images
        Palette.Analyse(folderPath);

        //Analyse de l'image
        Analyse.analyseImage(imagePath);

        //Creation de l'image finale
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }
}
