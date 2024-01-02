import java.io.IOException;
import java.util.ArrayList;

public interface IImageScraper {

    String getResponse();
    Object getImageSources();
    ArrayList<String> getImagePathes();
    public String fetchContent(final String url) throws IOException, InterruptedException;
}
