import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URI;

public class BookWalkerScraper extends ImageScraper{

    //1つのURLをコンストラクタで受け取る
    public BookWalkerScraper(final String url) {
        super(validateBookWalkerUrl(url));
    }

    //複数ののURLを使用する
    public BookWalkerScraper(final String[] urls) {
        super(validateBookWalkerUrl(urls));
    }

    //URLがBookWalkerの物であることをバリデーション
    private static String validateBookWalkerUrl(String url) {
        if (url == null || !url.contains("bookwalker.jp")) {
            throw new IllegalArgumentException("URL should be BookWalker one");
        }
        return url;
    }

    private static String[] validateBookWalkerUrl(String[] urls) {
        for(String url : urls) {
            validateBookWalkerUrl(url);
        }
        return urls;
    }

    //GETしたHTMLから必要な要素を抽出
    protected void extractImageSources() {
        Document doc = Jsoup.parse(getResponse());
        Elements links = doc.select("a.m-thumb__image");

        for (Element link : links) {
            Elements images = link.select("img"); // 各aタグ内のimgタグを選択
            for (Element image : images) {
                String src = image.attr("abs:data-original"); // imgタグのsrc属性を取得

                if (src == null || src.isEmpty()) {
                    src = image.attr("abs:src"); // Fallback for lazy loading
                }
                
                try {
                    getImageSources().add(URI.create(src).toURL());
                } catch (Exception e) {
                    System.err.println(e);       
                }
            }
        }
    }    

    protected void downloadImage() {
        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.downloadImage(getImageSources(), "BookWalker");
        setImagePathes(imageDownloader.getImagePaths());
    }

}