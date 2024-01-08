import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * The `ImageScraper` class is an abstract class that provides methods for fetching content from URLs,
 * extracting image sources, and downloading images.
 */
public abstract class ImageScraper implements IImageScraper{
    
    private String response;
    private ArrayList<URL> imageSources;
    private ArrayList<String> imagePaths;

    //urlが単一の場合
    public ImageScraper(final String url) {
        this.imageSources = new ArrayList<>();

        try {
            this.response = fetchContent(url);
        } catch(IOException | InterruptedException e) {
            System.err.println(e);
        }
        extractImageSources();
        downloadImage();
    }

    //urlが複数の場合
    public ImageScraper(final String[] urls) {
        this.imageSources = new ArrayList<>();

        for (String url : urls) {
            try {
                this.response = fetchContent(url);
            } catch(IOException | InterruptedException e) {
                System.err.println(e);
            }
            extractImageSources();
        }

        downloadImage();
    }

    /**
     * The function fetches the content of a given URL using HTTP GET request and
     * returns the response body
     * if the status code is 200, otherwise throws an IOException.
     * 
     * @param url The `url` parameter is a string that represents the URL of the
     *            content that you want to
     *            fetch.
     * @return The method fetchContent returns a String.
     */
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

    protected abstract void extractImageSources();
    protected abstract void downloadImage();
    

    public ArrayList<URL> getImageSources() {
        return this.imageSources;
    }

    public String getResponse() {
        return this.response;
    }

    protected void setImagePaths(final ArrayList<String> value) {
        this.imagePaths = value;
    }

    public ArrayList<String> getImagePaths() {
        return this.imagePaths;
    }
}