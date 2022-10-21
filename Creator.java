import Source.Image;

public class Creator {

    public static void main(String[] args) {
        String folderPath = "Entry/Folder";
        String imagePath = "Entry/Test.png";
        String resultPath = "Exit/Result.png";

        System.out.println("--------------------------------------------------------------");

        if (args.length == 0) {
            System.out.println("Using default settings");
        }
        System.out.println("");
        System.out.println("Starting with parameters:" + "\n" +
                "Folder path: " + folderPath + "\n" +
                "Image path: " + imagePath + "\n" +
                "Result path: " + resultPath + "\n");

        Image image = new Image(imagePath, folderPath, resultPath);
        image.createImage();

        System.out.println("End of program");
        System.out.println("--------------------------------------------------------------");
    }
}