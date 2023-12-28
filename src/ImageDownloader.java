import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;    
import java.net.URL;
import java.util.ArrayList;


public class ImageDownloader {
    public void downloadImage(ArrayList<URL> imageUrls) {
        String directoryPath = "downloads/img/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // ディレクトリが存在しない場合、作成する
        }

        for (int i = 0; i < imageUrls.size(); i++) {
            try (InputStream is = imageUrls.get(i).openStream();
                 OutputStream os = new FileOutputStream(directoryPath + String.format("%d.jpg", i))) {

                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                System.out.println("画像がダウンロードされました: " + directoryPath + String.format("%d.jpg", i));
            } catch (Exception e) {
                System.err.println("画像のダウンロードに失敗しました: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
