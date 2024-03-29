import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;    
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ImageDownloader {
    private ArrayList<String> imagePaths;

    public ImageDownloader() {
        imagePaths = new ArrayList<String>();
    }

    /**
     * The `downloadImage` function downloads a list of images from given URLs and
     * saves them to a
     * specified directory.
     * 
     * @param imageUrls    An ArrayList of URLs representing the image URLs that
     *                     need to be downloaded.
     * @param directoryName The `directoryName` parameter is a `String` that
     *                     represents the name of the
     *                     directory where the downloaded images will be saved.
     */
    public void downloadImage(ArrayList<URL> imageUrls, String directoryName) {
        String directoryPath = "downloads/img/" + directoryName + "/";
        Path dir = Paths.get(directoryPath);
        File directory = new File(directoryPath);
        
        //ディレクトリが存在しなければ作成
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //ディレクトリ内にファイルがあれば削除
        if (Files.exists(dir)) {
            try {
                Files.list(dir)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            System.err.println("Failed to delete a file");
                            e.printStackTrace();
                        }
                    });
            } catch (IOException e) {
                System.err.println("Failed to open the directory");
                e.printStackTrace();
            }
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
                
                System.out.println("Download Succeed: " + path);
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
