import java.util.ArrayList;

public class ImageAnalyzer {
    private ArrayList<UserImage> imageDataSet;
    private int[] RValueAverage;
    private int[] GValueAverage;
    private int[] BValueAverage;
    private int[] RValueMedian;
    private int[] GValueMedian;
    private int[] BValueMedian;
    private int[] LuminanceAverage;
    private int[] LuminanceMedian;

    public ImageAnalyzer(final ArrayList<String> imagePaths) {
        this.imageDataSet = ImageDataInitializer.initializeData(imagePaths);
        this.calculateStatistics();
        for (int i = 0; i < 256; i++) {
            System.err.println(i + ":" + RValueAverage[i]);
        }
    }

    private void calculateStatistics() {
        ImageStatisticsCalculator calculator = new ImageStatisticsCalculator(imageDataSet);
        this.RValueAverage = calculator.calculateAverageRValue();
        this.GValueAverage = calculator.calculateAverageGValue();
        this.BValueAverage = calculator.calculateAverageBValue();
        this.RValueMedian = calculator.calculateMedianRValue();
        this.GValueMedian = calculator.calculateMedianGValue();
        this.BValueMedian = calculator.calculateMedianBValue();
        this.LuminanceAverage = calculator.calculateAverageLuminance();
        this.LuminanceMedian = calculator.calculateMedianLuminance();
    }

    private ArrayList<UserImage> getImageDataSet() {
        return this.imageDataSet;
    }

    public int[] getRValueAverage() {
        return this.RValueAverage;
    }

    public int[] getGValueAverage() {
        return this.GValueAverage;
    }

    public int[] getBValueAverage() {
        return this.BValueAverage;
    }

    public int[] getRValueMedian() {
        return this.RValueMedian;
    }

    public int[] getGValueMedian() {
        return this.GValueMedian;
    }

    public int[] getBValueMedian() {
        return this.BValueMedian;
    }

    public int[] getLuminanceAverage() {
        return this.LuminanceAverage;
    }

    public int[] getLuminanceMedian() {
        return this.LuminanceMedian;
    }
}
