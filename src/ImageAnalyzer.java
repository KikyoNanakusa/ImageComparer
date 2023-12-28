import java.util.ArrayList;

public class ImageAnalyzer {
    private ArrayList<String> imagePaths;
    private ArrayList<UserImage> imageDataSet;
    private int[] RvalueAverage;
    private int[] GvalueAverage;
    private int[] BvalueAverage;


    public ImageAnalyzer(final ArrayList<String> imagePaths) {
        initializeData(imagePaths);

        calclateAverageRvalue();
        calclateAverageGvalue();
        calclateAverageBvalue();
        for (int i = 0; i < this.RvalueAverage.length; i++) {
            System.out.println(i + " : " + this.RvalueAverage[i]);
        }
    }

    private void initializeData(ArrayList<String> imagePaths) {
        this.imageDataSet = new ArrayList<UserImage>();
        this.imagePaths = imagePaths;
        for (String path : imagePaths) {
            imageDataSet.add(new UserImage(path));
        }
    }

    private void calclateAverageRvalue() {
        int[] counts = new int[256];
        
        for (UserImage image : getImageDataSet()) {
            int[] RvalueDistribution = image.getColorData().getRvalueDistribution();
            for (int i = 0; i < counts.length; i++) {
                counts[i] += RvalueDistribution[i];
            }
        }

        for (int i = 0; i < counts.length; i++) {
            counts[i] /= getImageDataSet().size();
        }

        this.RvalueAverage = counts;
    }

    private void calclateAverageGvalue() {
        int[] counts = new int[256];
        
        for (UserImage image : getImageDataSet()) {
            int[] GvalueDistribution = image.getColorData().getGvalueDistribution();
            for (int i = 0; i < counts.length; i++) {
                counts[i] += GvalueDistribution[i];
            }
        }

        for (int i = 0; i < counts.length; i++) {
            counts[i] /= getImageDataSet().size();
        }

        this.GvalueAverage = counts;
    }

    private void calclateAverageBvalue() {
        int[] counts = new int[256];
        
        for (UserImage image : getImageDataSet()) {
            int[] BvalueDistribution = image.getColorData().getBvalueDistribution();
            for (int i = 0; i < counts.length; i++) {
                counts[i] += BvalueDistribution[i];
            }
        }

        for (int i = 0; i < counts.length; i++) {
            counts[i] /= getImageDataSet().size();
        }

        this.BvalueAverage = counts;
    }

    private ArrayList<UserImage> getImageDataSet() {
        return this.imageDataSet;
    }

    public int[] getRvalueAverage() {
        return this.RvalueAverage;
    }

    public int[] getGvalueAverage() {
        return this.GvalueAverage;
    }

    public int[] getBvalueAverage() {
        return this.BvalueAverage;
    }
}
