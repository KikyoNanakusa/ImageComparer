import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookWalkerScraper {
    
    private String response;
    private String url;

    public BookWalkerScraper() {
        try {
            this.response = sendGetRequest("https://bookwalker.jp/new/?qsto=st2");
        } catch(IOException | InterruptedException e) {
            System.err.println(e);
        }

        extractImages();
    }

    public static String sendGetRequest(String url) throws IOException, InterruptedException {
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

    public void extractImages() {
        Document doc = Jsoup.parse(this.response);
        Elements links = doc.select("a.m-thumb__image");

        for (Element link : links) {
            Elements images = link.select("img"); // 各aタグ内のimgタグを選択
            for (Element image : images) {
                String src = image.attr("src"); // imgタグのsrc属性を取得
                System.out.println("Image Source: " + src);
            }
        }
    }
}