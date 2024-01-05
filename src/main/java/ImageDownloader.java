import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;    
import java.net.URL;
import java.util.ArrayList;


public class ImageDownloader {
    private ArrayList<String> imagePaths;

    public ImageDownloader() {
        imagePaths = new ArrayList<String>();
    }

    public void downloadImage(ArrayList<URL> imageUrls, String directryName) {
        String directoryPath = "downloads/img/" + directryName + "/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (int i = 0; i < imageUrls.size(); i++) {
            String path = directoryPath + String.format("%d.jpg", i);

            try (InputStream is = imageUrls.get(i).openStream();
                 OutputStream os = new FileOutputStream(path)) {

                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                
                System.out.println("Download Scceed: " + path);
                imagePaths.add(path);
            } catch (Exception e) {
                System.err.println("Download Failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getImagePaths() {
        return this.imagePaths;
    }
}
