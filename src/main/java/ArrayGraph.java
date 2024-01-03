import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ArrayGraph extends Application {
   private static long[] arrayData;

   public ArrayGraph() {

    }

   @Override
   public void start(Stage stage) {
       stage.setTitle("Array Graph");

       // Defining the axes
       final NumberAxis xAxis = new NumberAxis();
       final NumberAxis yAxis = new NumberAxis();
       xAxis.setLabel("Index");

       // Creating the chart
       final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

       lineChart.setTitle("Array Data");

       // Defining a series
       XYChart.Series series = new XYChart.Series();
       series.setName("Array Elements");

       // Populating the series with data
       for (int i = 0; i < arrayData.length; i++) {
           series.getData().add(new XYChart.Data(i, arrayData[i]));
       }

       Scene scene = new Scene(lineChart, 800, 600);
       lineChart.getData().add(series);

       stage.setScene(scene);
       stage.show();
   }

    public static void main(String[] args) {
        IImageScraper bookwalkerScraper = new BookWalkerScraper("https://bookwalker.jp/new/?qsto=st2");
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(bookwalkerScraper.getImagePathes());
        arrayData = imageAnalyzer.getRValueAverage();
        launch(args);
    }
}