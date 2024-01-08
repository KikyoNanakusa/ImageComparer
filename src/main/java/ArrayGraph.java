import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
/**
 * The ArrayGraph class is a Java application that creates a line chart to display multiple data series
 * from arrays.
 */
public class ArrayGraph extends Application {
    private List<long[]> dataArrays;
    private List<long[]> userImageDataArrays;
    private List<String> dataNames;
    private List<String> userDataNames;
    private Map<String, Pair<long[], long[]>> linkedData;
    private Map<String, String> seriesColors;
    private LineChart<Number, Number> lineChart;

    public ArrayGraph() {
        dataArrays = new ArrayList<>();
        userDataNames = new ArrayList<>();
        userImageDataArrays = new ArrayList<>();
        linkedData = new HashMap<>();
        dataNames = new ArrayList<>();
        seriesColors = new HashMap<>();
        initializeData();
    }

    //グラフに表示するデータを初期化、データごとに色を設定
    private void initializeData() {
        initializeDatasetImage();
        initializeUserImageData();
        dataLinking();
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

    private void dataLinking() {
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
        // 既存のデータと新しいユーザーデータを紐付け
        linkedData.put(name, new Pair<>(existingData, userData));
    }

    private long[] findDataByName(String name) {
        int index = dataNames.indexOf(name);
        if (index != -1) {
            return dataArrays.get(index);
        }
        return null; // またはエラー処理
    }

    private long[] findUserDataByName(String name) {
        int index = userDataNames.indexOf(name);
        if (index != -1) {
            return userImageDataArrays.get(index);
        }
        return null; // またはエラー処理
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Array Graph");
        
        // ステージのサイズを設定
        stage.setWidth(1000); // 幅
        stage.setHeight(600); // 高さ

        // 軸の設定
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Index");

        // LineChartの設定
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Array Data");

        // ComboBoxの設定
        ComboBox<String> seriesSelector = new ComboBox<>();
        seriesSelector.getItems().addAll(linkedData.keySet()); // linkedDataのキーを使用
        seriesSelector.getSelectionModel().selectFirst();
        seriesSelector.valueProperty().addListener((obs, oldVal, newVal) -> updateChartData(newVal, lineChart));

        // 初期データのグラフ描画
        updateChartData(seriesSelector.getValue(), lineChart);

        // VBoxにUIコンポーネントを追加
        VBox vbox = new VBox(seriesSelector, lineChart);
        Scene scene = new Scene(vbox, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void updateChartData(String selectedSeries, LineChart<Number, Number> lineChart) {
        Pair<long[], long[]> dataPair = linkedData.get(selectedSeries);

        lineChart.getData().clear();
        lineChart.setPrefSize(800, 500);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        XYChart.Series<Number, Number> userSeries = new XYChart.Series<>();

        series.setName(selectedSeries);
        userSeries.setName("User " + selectedSeries);

        // 既存のデータとユーザーデータの両方をシリーズに追加
        if (dataPair != null) {
            long[] existingData = dataPair.getKey();
            long[] userData = dataPair.getValue();

            for (int i = 0; i < existingData.length; i++) {
                series.getData().add(new XYChart.Data<>(i, existingData[i]));
            }

            for (int i = 0; i < userData.length; i++) {
                userSeries.getData().add(new XYChart.Data<>(i, userData[i]));
            }
        }

        lineChart.getData().addAll(series, userSeries);
        applySeriesColor(series, seriesColors.getOrDefault(selectedSeries, "black"));
        applySeriesColor(userSeries, seriesColors.getOrDefault("User Image " + selectedSeries, "gray"));
    }


    /**
     * The function `applySeriesColor` applies a specified color to the line,
     * markers, and legend item of a
     * given series in a JavaFX XYChart.
     * 
     * @param series The series parameter is an instance of the XYChart.Series
     *               class, which represents a
     *               series of data points in a chart. It contains the data points
     *               and other properties of the series,
     *               such as the name and the chart it belongs to.
     * @param color  The color parameter is a string representing the color that you
     *               want to apply to the
     *               series. It should be in a format that is compatible with CSS
     *               color values, such as "#RRGGBB" for a
     *               hexadecimal color or "rgb(R, G, B)" for an RGB color.
     */
    private void applySeriesColor(XYChart.Series<Number, Number> series, String color) {
        // シリーズの線に色を適用
        Node line = series.getNode().lookup(".chart-series-line");
        if (line != null) {
            line.setStyle("-fx-stroke: " + color + ";");
        }

        // 各データポイント（マーカー）に色とサイズを適用
        for (XYChart.Data<Number, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {
                node.setStyle("-fx-background-color: " + color + ", white; -fx-padding: 2px;");
            }
        }

        // レジェンドアイテムの色を変更
        for (Node n : lineChart.lookupAll(".chart-legend-item")) {
            if (n instanceof Label && ((Label) n).getText().equals(series.getName())) {
                ((Label) n).getGraphic().setStyle("-fx-background-color: " + color + ";");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}