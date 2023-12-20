import java.awt.image.BufferedImage;

class ColorData {
    private int[][] RGB;
    private int[][] R;
    private int[][] G;
    private int[][] B;

    public ColorData(BufferedImage image) {
        InitRGB(image);
    }

    private void InitRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        this.RGB = new int[width][height];

        //RGBを二次元配列に格納
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = image.getRGB(x, y);
                this.RGB[x][y] = color;
                this.R[x][y] = ( color >> 16 ) & 0xff;
                this.G[x][y] = ( color >> 8 ) & 0xff;
                this.B[x][y] = color & 0xff;
            }
        }
    }

    public int[][] getRGB() {
        return this.RGB;
    }

    public int[][] getR() {
        return this.R;
    }

    public int[][] getG() {
        return this.G;
    }
    
    public int[][] getB() {
        return this.B;
    }
    
}