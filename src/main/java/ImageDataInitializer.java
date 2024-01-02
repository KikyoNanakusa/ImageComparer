import java.util.ArrayList;

public class ImageDataInitializer {
    public  static ArrayList<UserImage> initializeData(ArrayList<String> imagePaths) {
        ArrayList<UserImage> imageDataSet = new ArrayList<>();
        for (String path : imagePaths) {
            imageDataSet.add(new UserImage(path));
        }
        return imageDataSet;
    }
}
