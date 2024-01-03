import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ArrayGraph extends Application {
    private List<long[]> dataArrays;
    private List<String> dataNames;

    public ArrayGraph() {
        dataArrays = new ArrayList<>();
        dataNames = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        IImageScraper bookwalkerScraper = new BookWalkerScraper("https://bookwalker.jp/new/?qsto=st2");
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(bookwalkerScraper.getImagePathes());

        addData("R Value Average", imageAnalyzer.getRValueAverage());
        addData("G Value Average", imageAnalyzer.getGValueAverage());
        addData("B Value Average", imageAnalyzer.getBValueAverage());
        addData("Luminance Value Average", imageAnalyzer.getLuminanceAverage());
        addData("R Value Median", imageAnalyzer.getRValueMedian());
        addData("G Value Median", imageAnalyzer.getGValueMedian());
        addData("B Value Median", imageAnalyzer.getBValueMedian());
        addData("Luminance Value Median", imageAnalyzer.getLuminanceMedian());
    }

    private void addData(String name, long[] data) {
        dataNames.add(name);
        dataArrays.add(data);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Array Graph");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Index");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Array Data");

        ComboBox<String> seriesSelector = new ComboBox<>();
        seriesSelector.getItems().addAll(dataNames);
        seriesSelector.getSelectionModel().selectFirst();
        seriesSelector.valueProperty().addListener((obs, oldVal, newVal) -> updateChartData(newVal, lineChart));

        updateChartData(seriesSelector.getValue(), lineChart);

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}