package main.java;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageStatisticsCalculator {
    private final int RGB_MAX = 256;
    private ArrayList<UserImage> imageDataSet;

    public ImageStatisticsCalculator(ArrayList<UserImage> imageDataSet) {
        this.imageDataSet = imageDataSet;
    }

    public long[] calculateAverageRValue() {
        return calculateAverageValue(ColorChannel.RED);
    }

    public long[] calculateAverageGValue() {
        return calculateAverageValue(ColorChannel.GREEN);
    }

    public long[] calculateAverageBValue() {
        return calculateAverageValue(ColorChannel.BLUE);
    }

    public long[] calculateMedianRValue() {
        return calculateMedianValue(ColorChannel.RED);
    }

    public long[] calculateMedianGValue() {
        return calculateMedianValue(ColorChannel.GREEN);
    }

    public long[] calculateMedianBValue() {
        return calculateMedianValue(ColorChannel.BLUE);
    }

    public long[] calculateAverageLuminance() {
        return calculateAverageValue(ColorChannel.LUMINANCE);
    }

    public long[] calculateMedianLuminance() {
        return calculateMedianValue(ColorChannel.LUMINANCE);
    }

    private long[] calculateAverageValue(ColorChannel channel) {
        long[] counts = new long[RGB_MAX];
        for (UserImage image : imageDataSet) {
            long[] valueDistribution = getColorDistribution(image, channel);
            for (int i = 0; i < RGB_MAX; i++) {
                counts[i] += valueDistribution[i];
            }
        }
        for (int i = 0; i < RGB_MAX; i++) {
            counts[i] /= imageDataSet.size();
        }
        return counts;
    }

    private long[] calculateMedianValue(ColorChannel channel) {
        int imageDataSetSize = imageDataSet.size();
        long[][] total = new long[RGB_MAX][imageDataSetSize];
        long[] median = new long[RGB_MAX];

        for (int i = 0; i < imageDataSetSize; i++) {
            UserImage image = imageDataSet.get(i);
            long[] valueDistribution = getColorDistribution(image, channel);
            for (int j = 0; j < RGB_MAX; j++) {
                total[j][i] = valueDistribution[j];
            }
        }

        for (int i = 0; i < RGB_MAX; i++) {
            Arrays.sort(total[i]);
            median[i] = findMedian(total[i]);
        }

        return median;
    }

    private long[] calculateLuminanceDistribution(UserImage image) {
        long[] luminanceDistribution = new long[RGB_MAX];

        int[][] R = image.getColorData().getR();
        int[][] G = image.getColorData().getG();
        int[][] B = image.getColorData().getB();
        
        for (int x = 0; x < R.length; x++) {
            for (int y = 0; y < R[x].length; y++) {
                int luminance = calculateLuminance(R[x][y], G[x][y], B[x][y]);
                luminanceDistribution[luminance]++;
            }
        }
        return luminanceDistribution;
    }

    private int calculateLuminance(int red, int green, int blue) {
        return (int) (0.299 * red + 0.587 * green + 0.114 * blue);
    }


    private long[] getColorDistribution(UserImage image, ColorChannel channel) {
        switch (channel) {
            case RED:
                return convertToIntArray(image.getColorData().getRvalueDistribution());
            case GREEN:
                return convertToIntArray(image.getColorData().getGvalueDistribution());
            case BLUE:
                return convertToIntArray(image.getColorData().getBvalueDistribution());
            case LUMINANCE:
                return calculateLuminanceDistribution(image);
            default:
                return new long[RGB_MAX]; // 安全のためのデフォルトケース
        }
    }

    private long[] convertToIntArray(int[] array) {
        long[] longArray = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            longArray[i] = array[i];
        }
        return longArray;
    }

    private long findMedian(long[] arr) {
        int length = arr.length;
        if (length % 2 == 0) {
            long middle1 = arr[length / 2 - 1];
            long middle2 = arr[length / 2];
            return (middle1 + middle2) / 2;
        } else {
            return arr[length / 2];
        }
    }

    private enum ColorChannel {
        RED,
        GREEN,
        BLUE,
        LUMINANCE,
    }
}
