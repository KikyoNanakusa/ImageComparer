import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookWalkerScraper extends ImageScraper{
    protected void extractImages() {
        Document doc = Jsoup.parse(getResponse());
        Elements links = doc.select("a.m-thumb__image");

        for (Element link : links) {
            Elements images = link.select("img"); // 各aタグ内のimgタグを選択
            for (Element image : images) {
                String src = image.attr("abs:src"); // imgタグのsrc属性を取得
                if (src == null || src.isEmpty()) {
                    src = image.attr("abs:data-original"); // Fallback for lazy loading
                }
                System.out.println("Image Source: " + src);
                // getImageSources().add(src);
            }
        }
    }    
}