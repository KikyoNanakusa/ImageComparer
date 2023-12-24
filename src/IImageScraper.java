import java.io.IOException;

public interface IImageScraper {

    String getResponse();
    Object getImageSources();
    public String fetchContent(final String url) throws IOException, InterruptedException;
}
