import java.util.ArrayList;

public class ImageAnalyzer {
    private ArrayList<String> imagePathes;
    private ArrayList<UserImage> dataSet;

    public ImageAnalyzer(final ArrayList<String> imagePathes) {
        this.dataSet = new ArrayList<UserImage>();
        this.imagePathes = imagePathes;
        for (String path : imagePathes) {
            dataSet.add(new UserImage(path));
        }
        System.out.println(dataSet);
    }
}
