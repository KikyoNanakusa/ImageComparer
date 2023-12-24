import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public abstract class ImageScraper implements IImageScraper{
    
    private String response;
    private String url;
    private ArrayList<String> imageSources;

    public ImageScraper() {
        String testURL = "https://bookwalker.jp/new/?qsto=st2";
        try {
            this.response = fetchContent(testURL);
        } catch(IOException | InterruptedException e) {
            System.err.println(e);
        }
        extractImages();
    }

    public String fetchContent(final String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                return response.body();
            } else {
                throw new IOException("HTTPリクエストが失敗しました。ステータスコード: " + statusCode);
            }
    }

    protected abstract void extractImages();

    public ArrayList<String> getImageSources() {
        return this.imageSources;
    }

    public String getResponse() {
        return this.response;
    }
}