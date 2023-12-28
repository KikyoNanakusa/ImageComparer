import java.util.ArrayList;
import java.util.Arrays;

public class ImageStatisticsCalculator {
    private final int RGB_MAX = 256;
    private ArrayList<UserImage> imageDataSet;

    public ImageStatisticsCalculator(ArrayList<UserImage> imageDataSet) {
        this.imageDataSet = imageDataSet;
    }

    public int[] calculateAverageRValue() {
        return calculateAverageValue(ColorChannel.RED);
    }

    public int[] calculateAverageGValue() {
        return calculateAverageValue(ColorChannel.GREEN);
    }

    public int[] calculateAverageBValue() {
        return calculateAverageValue(ColorChannel.BLUE);
    }

    public int[] calculateMedianRValue() {
        return calculateMedianValue(ColorChannel.RED);
    }

    public int[] calculateMedianGValue() {
        return calculateMedianValue(ColorChannel.GREEN);
    }

    public int[] calculateMedianBValue() {
        return calculateMedianValue(ColorChannel.BLUE);
    }

    public int[] calculateAverageLuminance() {
        return calculateAverageValue(ColorChannel.LUMINANCE);
    }

    public int[] calculateMedianLuminance() {
        return calculateMedianValue(ColorChannel.LUMINANCE);
    }

    private int[] calculateAverageValue(ColorChannel channel) {
        int[] counts = new int[RGB_MAX];
        for (UserImage image : imageDataSet) {
            int[] valueDistribution = getColorDistribution(image, channel);
            for (int i = 0; i < RGB_MAX; i++) {
                counts[i] += valueDistribution[i];
            }
        }
        for (int i = 0; i < RGB_MAX; i++) {
            counts[i] /= imageDataSet.size();
        }
        return counts;
    }

    private int[] calculateMedianValue(ColorChannel channel) {
        int imageDataSetSize = imageDataSet.size();
        int[][] total = new int[RGB_MAX][imageDataSetSize];
        int[] median = new int[RGB_MAX];

        for (int i = 0; i < imageDataSetSize; i++) {
            UserImage image = imageDataSet.get(i);
            int[] valueDistribution = getColorDistribution(image, channel);
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

    private int[] calculateLuminanceDistribution(UserImage image) {
        int[] luminanceDistribution = new int[RGB_MAX];

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


    private int[] getColorDistribution(UserImage image, ColorChannel channel) {
        switch (channel) {
            case RED:
                return image.getColorData().getRvalueDistribution();
            case GREEN:
                return image.getColorData().getGvalueDistribution();
            case BLUE:
                return image.getColorData().getBvalueDistribution();
            case LUMINANCE:
                return calculateLuminanceDistribution(image);
            default:
                return new int[RGB_MAX]; // 安全のためのデフォルトケース
        }
    }

    private int findMedian(int[] arr) {
        int length = arr.length;
        if (length % 2 == 0) {
            int middle1 = arr[length / 2 - 1];
            int middle2 = arr[length / 2];
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
