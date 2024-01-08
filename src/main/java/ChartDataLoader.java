import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class ChartDataLoader {
    private final String R_VALUE_AVERAGE = "R Value Average";
    private final String G_VALUE_AVERAGE = "G Value Average";
    private final String B_VALUE_AVERAGE = "B Value Average";
    private final String LUMINANCE_VALUE_AVERAGE = "Luminance Value Average";
    private final String R_VALUE_MEDIAN = "R Value Median";
    private final String G_VALUE_MEDIAN = "G Value Median";
    private final String B_VALUE_MEDIAN = "B Value Median";
    private final String LUMINANCE_VALUE_MEDIAN = "Luminance Value Median";
    private final String USER_IMG_R_VALUE_AVERAGE = "User Image R Value Average";
    private final String USER_IMG_G_VALUE_AVERAGE = "User Image G Value Average";
    private final String USER_IMG_B_VALUE_AVERAGE = "User Image B Value Average";
    private final String USER_IMG_LUMINANCE_VALUE_AVERAGE = "User Image Luminance Value Average";
    private final String USER_IMG_R_VALUE_MEDIAN = "User Image R Value Median";
    private final String USER_IMG_G_VALUE_MEDIAN = "User Image G Value Median";
    private final String USER_IMG_B_VALUE_MEDIAN = "User Image B Value Median";
    private final String USER_IMG_LUMINANCE_VALUE_MEDIAN = "User Image Luminance Value Median";
    private final String COLOR_ORANGE = "orange";

    private List<long[]> dataArrays = new ArrayList<>();
    private List<long[]> userImageDataArrays = new ArrayList<>();
    private List<String> dataNames = new ArrayList<>();
    private List<String> userDataNames = new ArrayList<>();
    private Map<String, Pair<long[], long[]>> linkedData = new HashMap<>();
    private Map<String, String> seriesColors = new HashMap<>();

    public ChartDataLoader() {
        loadData();
    }

    private void loadData() {
        initializeDatasetImage();
        initializeUserImageData();
        linkDataSets();
    }

    private void initializeDatasetImage() {
        DatasetSelector datasetSelector = new DatasetSelector();
        datasetSelector.selectDataset();
        ImageAnalyzer dataSetImageAnalyzer = new ImageAnalyzer(datasetSelector.getImagePaths());

        addData(R_VALUE_AVERAGE, dataSetImageAnalyzer.getRValueAverage());
        addData(G_VALUE_AVERAGE, dataSetImageAnalyzer.getGValueAverage());
        addData(B_VALUE_AVERAGE, dataSetImageAnalyzer.getBValueAverage());
        addData(LUMINANCE_VALUE_AVERAGE, dataSetImageAnalyzer.getLuminanceAverage());
        addData(R_VALUE_MEDIAN, dataSetImageAnalyzer.getRValueMedian());
        addData(G_VALUE_MEDIAN, dataSetImageAnalyzer.getGValueMedian());
        addData(B_VALUE_MEDIAN, dataSetImageAnalyzer.getBValueMedian());
        addData(LUMINANCE_VALUE_MEDIAN, dataSetImageAnalyzer.getLuminanceMedian());

        seriesColors.put(R_VALUE_AVERAGE, "red");
        seriesColors.put(G_VALUE_AVERAGE, "green");
        seriesColors.put(B_VALUE_AVERAGE, "blue");
        seriesColors.put(LUMINANCE_VALUE_AVERAGE, "#808000");
        seriesColors.put(R_VALUE_MEDIAN, "red");
        seriesColors.put(G_VALUE_MEDIAN, "green");
        seriesColors.put(B_VALUE_MEDIAN, "blue");
        seriesColors.put(LUMINANCE_VALUE_MEDIAN, "#808000");
    }

    private void initializeUserImageData() {
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(new UserImagePathFinder().getImagePaths());
        addUserImageData(USER_IMG_R_VALUE_AVERAGE, imageAnalyzer.getRValueAverage());
        addUserImageData(USER_IMG_G_VALUE_AVERAGE, imageAnalyzer.getGValueAverage());
        addUserImageData(USER_IMG_B_VALUE_AVERAGE, imageAnalyzer.getBValueAverage());
        addUserImageData(USER_IMG_LUMINANCE_VALUE_AVERAGE, imageAnalyzer.getLuminanceAverage());
        addUserImageData(USER_IMG_R_VALUE_MEDIAN, imageAnalyzer.getRValueMedian());
        addUserImageData(USER_IMG_G_VALUE_MEDIAN, imageAnalyzer.getGValueMedian());
        addUserImageData(USER_IMG_B_VALUE_MEDIAN, imageAnalyzer.getBValueMedian());
        addUserImageData(USER_IMG_LUMINANCE_VALUE_MEDIAN, imageAnalyzer.getLuminanceMedian());

        seriesColors.put(USER_IMG_R_VALUE_AVERAGE, COLOR_ORANGE);
        seriesColors.put(USER_IMG_G_VALUE_AVERAGE, COLOR_ORANGE);
        seriesColors.put(USER_IMG_B_VALUE_AVERAGE, COLOR_ORANGE);
        seriesColors.put(USER_IMG_LUMINANCE_VALUE_AVERAGE, COLOR_ORANGE);
        seriesColors.put(USER_IMG_R_VALUE_MEDIAN, COLOR_ORANGE);
        seriesColors.put(USER_IMG_G_VALUE_MEDIAN, COLOR_ORANGE);
        seriesColors.put(USER_IMG_B_VALUE_MEDIAN, COLOR_ORANGE);
        seriesColors.put(USER_IMG_LUMINANCE_VALUE_MEDIAN, COLOR_ORANGE);
    }

    private void linkDataSets() {
        addLinkedData(R_VALUE_AVERAGE, findDataByName(R_VALUE_AVERAGE),
                findUserDataByName(USER_IMG_R_VALUE_AVERAGE));
        addLinkedData(G_VALUE_AVERAGE, findDataByName(G_VALUE_AVERAGE),
                findUserDataByName(USER_IMG_B_VALUE_AVERAGE));
        addLinkedData(B_VALUE_AVERAGE, findDataByName(B_VALUE_AVERAGE),
                findUserDataByName(USER_IMG_G_VALUE_AVERAGE));
        addLinkedData(LUMINANCE_VALUE_AVERAGE, findDataByName(LUMINANCE_VALUE_AVERAGE),
                findUserDataByName(USER_IMG_LUMINANCE_VALUE_AVERAGE));
        addLinkedData(R_VALUE_MEDIAN, findDataByName(R_VALUE_MEDIAN),
                findUserDataByName(USER_IMG_R_VALUE_MEDIAN));
        addLinkedData(G_VALUE_MEDIAN, findDataByName(G_VALUE_MEDIAN),
                findUserDataByName(USER_IMG_G_VALUE_MEDIAN));
        addLinkedData(B_VALUE_MEDIAN, findDataByName(B_VALUE_MEDIAN),
                findUserDataByName(USER_IMG_B_VALUE_MEDIAN));
        addLinkedData(LUMINANCE_VALUE_MEDIAN, findDataByName(LUMINANCE_VALUE_MEDIAN),
                findUserDataByName(USER_IMG_LUMINANCE_VALUE_MEDIAN));
    }

    private void addData(String name, long[] data) {
        dataNames.add(name);
        dataArrays.add(data);
    }

    private void addUserImageData(String name, long[] data) {
        userDataNames.add(name);
        userImageDataArrays.add(data);
    }

    private void addLinkedData(String name, long[] existingData, long[] userData) {
        linkedData.put(name, new Pair<>(existingData, userData));
    }

    public long[] findDataByName(String name) {
        int index = dataNames.indexOf(name);
        return index != -1 ? dataArrays.get(index) : null;
    }

    public long[] findUserDataByName(String name) {
        int index = userDataNames.indexOf(name);
        return index != -1 ? userImageDataArrays.get(index) : null;
    }

    // ゲッターメソッドを追加
    public List<long[]> getDataArrays() {
        return dataArrays;
    }

    public List<long[]> getUserImageDataArrays() {
        return userImageDataArrays;
    }

    public Map<String, Pair<long[], long[]>> getLinkedData() {
        return linkedData;
    }

    public Map<String, String> getSeriesColors() {
        return seriesColors;
    }
}
