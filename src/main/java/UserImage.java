import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserImage {
    private BufferedImage image;
    private ImageSize size;
    private ColorData color;

    public UserImage(final String filePath) {
        loadImage(filePath);
        this.size = new ImageSize(this.image.getHeight(), this.image.getHeight());
        this.color = new ColorData(image);
    }

    private void loadImage(String filePath) {
        try {
            File file = new File(filePath);
            this.image = ImageIO.read(file);
            System.out.println("画像の読み込みが完了しました。" + filePath);
        } catch (IOException e) {
            System.err.println("画像の読み込み中にエラーが発生しました: " + e.getMessage());
        }
    }

    public ImageSize getSize() {
        return this.size;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public ColorData getColorData() {
        return this.color;
    }
}
