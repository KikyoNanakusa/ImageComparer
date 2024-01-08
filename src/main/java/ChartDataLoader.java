import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class ChartDataLoader {
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

        addData("R Value Average", dataSetImageAnalyzer.getRValueAverage());
        addData("G Value Average", dataSetImageAnalyzer.getGValueAverage());
        addData("B Value Average", dataSetImageAnalyzer.getBValueAverage());
        addData("Luminance Value Average", dataSetImageAnalyzer.getLuminanceAverage());
        addData("R Value Median", dataSetImageAnalyzer.getRValueMedian());
        addData("G Value Median", dataSetImageAnalyzer.getGValueMedian());
        addData("B Value Median", dataSetImageAnalyzer.getBValueMedian());
        addData("Luminance Value Median", dataSetImageAnalyzer.getLuminanceMedian());

        seriesColors.put("R Value Average", "red");
        seriesColors.put("G Value Average", "green");
        seriesColors.put("B Value Average", "blue");
        seriesColors.put("Luminance Value Average", "#808000");
        seriesColors.put("R Value Median", "red");
        seriesColors.put("G Value Median", "green");
        seriesColors.put("B Value Median", "blue");
        seriesColors.put("Luminance Value Median", "#808000");
    }

    private void initializeUserImageData() {
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(new UserImagePathFinder().getImagePaths());
        addUserImageData("User Image R Value Average", imageAnalyzer.getRValueAverage());
        addUserImageData("User Image G Value Average", imageAnalyzer.getGValueAverage());
        addUserImageData("User Image B Value Average", imageAnalyzer.getBValueAverage());
        addUserImageData("User Image Luminance Value Average", imageAnalyzer.getLuminanceAverage());
        addUserImageData("User Image R Value Median", imageAnalyzer.getRValueMedian());
        addUserImageData("User Image G Value Median", imageAnalyzer.getGValueMedian());
        addUserImageData("User Image B Value Median", imageAnalyzer.getBValueMedian());
        addUserImageData("User Image Luminance Value Median", imageAnalyzer.getLuminanceMedian());
        
        seriesColors.put("User Image R Value Average", "orange");
        seriesColors.put("User Image G Value Average", "orange");
        seriesColors.put("User Image B Value Average", "orange");
        seriesColors.put("User Image Luminance Value Average", "orange");
        seriesColors.put("User Image R Value Median", "orange");
        seriesColors.put("User Image G Value Median", "orange");
        seriesColors.put("User Image B Value Median", "orange");
        seriesColors.put("User Image Luminance Value Median", "orange");
    }

    private void linkDataSets() {
        addLinkedData("R Value Average", findDataByName("R Value Average"),
                findUserDataByName("User Image R Value Average"));
        addLinkedData("G Value Average", findDataByName("G Value Average"),
                findUserDataByName("User Image G Value Average"));
        addLinkedData("B Value Average", findDataByName("B Value Average"),
                findUserDataByName("User Image B Value Average"));
        addLinkedData("Luminance Value Average", findDataByName("Luminance Value Average"),
                findUserDataByName("User Image Luminance Value Average"));
        addLinkedData("R Value Median", findDataByName("R Value Median"),
                findUserDataByName("User Image R Value Median"));
        addLinkedData("G Value Median", findDataByName("G Value Median"),
                findUserDataByName("User Image G Value Median"));
        addLinkedData("B Value Median", findDataByName("B Value Median"),
                findUserDataByName("User Image B Value Median"));
        addLinkedData("Luminance Value Median", findDataByName("Luminance Value Median"),
                findUserDataByName("User Image Luminance Value Median"));
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
