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
            System.out.println("Image loaded : " + filePath);
        } catch (IOException e) {
            System.err.println("Image load failed" + e.getMessage());
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
