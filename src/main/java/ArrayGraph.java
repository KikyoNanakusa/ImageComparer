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
import javafx.util.Pair;

/**
 * The ArrayGraph class is a JavaFX application that displays a line chart with data series and allows
 * the user to select and update the displayed series.
 */
public class ArrayGraph extends Application {
    private ChartData chartData;
    private LineChart<Number, Number> lineChart;

    public ArrayGraph() {
        chartData = new ChartData();
    }

    /**
     * This function sets up a JavaFX stage with a line chart and a combo box, and
     * updates the chart data
     * based on the selected value in the combo box.
     * 
     * @param stage The stage parameter represents the main window or stage of the
     *              JavaFX application. It
     *              is used to set the title, size, and scene of the stage.
     */
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
        seriesSelector.getItems().addAll(chartData.getLinkedData().keySet()); // linkedDataのキーを使用
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

    /**
     * The function `updateChartData` updates a LineChart with new data series and
     * applies colors to the
     * series.
     * 
     * @param selectedSeries The selected series is a String that represents the
     *                       name of the series to be
     *                       updated in the chart. It is used to retrieve the
     *                       corresponding data from the chartData object.
     * @param lineChart      The `lineChart` parameter is an instance of the
     *                       `LineChart<Number, Number>` class.
     *                       It represents the line chart where the data will be
     *                       updated.
     */
    private void updateChartData(String selectedSeries, LineChart<Number, Number> lineChart) {
        Pair<long[], long[]> dataPair = chartData.getLinkedData().get(selectedSeries);

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
        applySeriesColor(series, chartData.getSeriesColors().getOrDefault(selectedSeries, "black"));
        applySeriesColor(userSeries, chartData.getSeriesColors().getOrDefault("User Image " + selectedSeries, "gray"));
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