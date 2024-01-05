import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Node;
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

public class ArrayGraph extends Application {
    private List<long[]> dataArrays;
    private List<String> dataNames;
    private Map<String, String> seriesColors;
    private LineChart<Number, Number> lineChart;

    public ArrayGraph() {
        dataArrays = new ArrayList<>();
        dataNames = new ArrayList<>();
        seriesColors = new HashMap<>();
        initializeData();
    }

    private void initializeData() {
        IImageScraper bookwalkerScraper = new BookWalkerScraper(BookWalkerURLs.URLS);
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(bookwalkerScraper.getImagePathes());

        addData("R Value Average", imageAnalyzer.getRValueAverage());
        addData("G Value Average", imageAnalyzer.getGValueAverage());
        addData("B Value Average", imageAnalyzer.getBValueAverage());
        addData("Luminance Value Average", imageAnalyzer.getLuminanceAverage());
        addData("R Value Median", imageAnalyzer.getRValueMedian());
        addData("G Value Median", imageAnalyzer.getGValueMedian());
        addData("B Value Median", imageAnalyzer.getBValueMedian());
        addData("Luminance Value Median", imageAnalyzer.getLuminanceMedian());

        seriesColors.put("R Value Average", "red");
        seriesColors.put("G Value Average", "green");
        seriesColors.put("B Value Average", "blue");
        seriesColors.put("Luminance Value Average", "#808000");
        seriesColors.put("R Value Median", "red");
        seriesColors.put("G Value Median", "green");
        seriesColors.put("B Value Median", "blue");
        seriesColors.put("Luminance Value Median", "#808000");
    }

    private void addData(String name, long[] data) {
        dataNames.add(name);
        dataArrays.add(data);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Array Graph");
    
        // 軸の設定
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Index");
    
        // LineChartの設定
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Array Data");
    
        // ComboBoxの設定
        ComboBox<String> seriesSelector = new ComboBox<>();
        seriesSelector.getItems().addAll(dataNames);
        seriesSelector.getSelectionModel().selectFirst();
        seriesSelector.valueProperty().addListener((obs, oldVal, newVal) -> 
            updateChartData(newVal, lineChart)
        );
    
        // 初期データのグラフ描画
        updateChartData(seriesSelector.getValue(), lineChart);
    
        // VBoxにUIコンポーネントを追加
        VBox vbox = new VBox(seriesSelector, lineChart);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void updateChartData(String selectedSeries, LineChart<Number, Number> lineChart) {
        int index = dataNames.indexOf(selectedSeries);
        long[] selectedData = dataArrays.get(index);
    
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName(selectedSeries);
    
        for (int i = 0; i < selectedData.length; i++) {
            series.getData().add(new XYChart.Data(i, selectedData[i]));
        }
    
        lineChart.getData().add(series);
        applySeriesColor(series, seriesColors.get(selectedSeries));
    }
    
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
                // 色とサイズの設定
                node.setStyle("-fx-background-color: " + color + ", white; -fx-padding: 2px;");
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}