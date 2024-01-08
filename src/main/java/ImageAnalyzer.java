import java.util.ArrayList;

public class ImageAnalyzer {
    private ArrayList<UserImage> imageDataSet;
    private long[] RValueAverage;
    private long[] GValueAverage;
    private long[] BValueAverage;
    private long[] RValueMedian;
    private long[] GValueMedian;
    private long[] BValueMedian;
    private long[] LuminanceAverage;
    private long[] LuminanceMedian;

    public ImageAnalyzer(final ArrayList<String> imagePaths) {
        this.imageDataSet = ImageDataInitializer.initializeData(imagePaths);
        this.calculateStatistics();
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

    public long[] getRValueAverage() {
        return this.RValueAverage;
    }

    public long[] getGValueAverage() {
        return this.GValueAverage;
    }

    public long[] getBValueAverage() {
        return this.BValueAverage;
    }

    public long[] getRValueMedian() {
        return this.RValueMedian;
    }

    public long[] getGValueMedian() {
        return this.GValueMedian;
    }

    public long[] getBValueMedian() {
        return this.BValueMedian;
    }

    public long[] getLuminanceAverage() {
        return this.LuminanceAverage;
    }

    public long[] getLuminanceMedian() {
        return this.LuminanceMedian;
    }
}
