import java.util.ArrayList;

public class ImageAnalyzer {
    private ArrayList<String> imagePathes;
    private ArrayList<UserImage> imageDataSet;

    public ImageAnalyzer(final ArrayList<String> imagePathes) {
        InitializeData(imagePathes);
    }

    private void InitializeData(ArrayList<String> imagePathes) {
        this.imageDataSet = new ArrayList<UserImage>();
        this.imagePathes = imagePathes;
        for (String path : imagePathes) {
            imageDataSet.add(new UserImage(path));
        }
    }

    private void calclateAverageRValue() {
        int[] counts = new int[257];

        for (UserImage image : getImageDataSet()) {
            image.getColorData().getR()
        }
    }


    private ArrayList<UserImage> getImageDataSet() {
        return this.imageDataSet;
    }
}
