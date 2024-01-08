import java.io.IOException;
import java.util.ArrayList;

public interface IImageScraper {

    String getResponse();
    Object getImageSources();
    ArrayList<String> getImagePaths();
    public String fetchContent(final String url) throws IOException, InterruptedException;
}
