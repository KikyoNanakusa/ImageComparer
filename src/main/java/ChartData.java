import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * The ChartData class is responsible for loading and providing access to various data arrays, user
 * image data arrays, linked data, and series colors for a chart.
 */
public class ChartData {
    private List<long[]> dataArrays;
    private List<long[]> userImageDataArrays;
    private Map<String, Pair<long[], long[]>> linkedData;
    private Map<String, String> seriesColors;

    public ChartData() {
        ChartDataLoader loader = new ChartDataLoader();
        this.dataArrays = loader.getDataArrays();
        this.userImageDataArrays = loader.getUserImageDataArrays();
        this.linkedData = loader.getLinkedData();
        this.seriesColors = loader.getSeriesColors();
    }

    public List<long[]> getDataArrays() {
        return this.dataArrays;
    }

    public List<long[]> getUserImageDataArrays() {
        return this.userImageDataArrays;
    }
    // リンクされたデータを取得
    public Map<String, Pair<long[], long[]>> getLinkedData() {
        return this.linkedData;
    }

    // シリーズの色情報を取得
    public Map<String, String> getSeriesColors() {
        return this.seriesColors;
    }
}
