import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public abstract class ImageScraper implements IImageScraper{
    
    private String response;
    private String url;
    private ArrayList<URL> imageSources;

    public ImageScraper() {
        this.imageSources = new ArrayList<>();

        this.url = "https://bookwalker.jp/new/?qsto=st2";
        try {
            this.response = fetchContent(this.url);
        } catch(IOException | InterruptedException e) {
            System.err.println(e);
        }
        extractImages();
        downloadImage();
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
    protected abstract void downloadImage();

    public ArrayList<URL> getImageSources() {
        return this.imageSources;
    }

    public String getResponse() {
        return this.response;
    }
}