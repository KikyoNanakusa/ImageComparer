import java.awt.image.BufferedImage;

class ColorData {
    private int[][] RGB;
    private int[][] R;
    private int[][] G;
    private int[][] B;
    private int[] RValueDistribution;
    private int[] GValueDistribution;
    private int[] BValueDistribution;


    public ColorData(BufferedImage image) {
        InitRGB(image);
    }

    private void InitRGB(BufferedImage image) {
        //Initialize variables
        int width = image.getWidth();
        int height = image.getHeight();
        this.RGB = new int[width][height];
        this.R = new int[width][height];
        this.G = new int[width][height];
        this.B = new int[width][height];


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

    private void setRValueDistribution() {
        this.RValueDistribution = new int[257];
        for (int[] rows : this.R) {
            for(int value : rows) {
                this.RValueDistribution[value] += 1;
            }
        }
    }

    private void setGValueDistribution() {
        this.RValueDistribution = new int[257];
        for (int[] rows : this.G) {
            for(int value : rows) {
                this.GValueDistribution[value] += 1;
            }
        }
    }

    private void setBValueDistribution() {
        this.RValueDistribution = new int[257];
        for (int[] rows : this.B) {
            for(int value : rows) {
                this.BValueDistribution[value] += 1;
            }
        }
    }

    public int[] getRValueDistribution() {
        if(this.RValueDistribution == null) {
            setRValueDistribution();
        }
        return this.RValueDistribution;
    }

    public int[] getGValueDistribution() {
        if(this.GValueDistribution == null) {
            setGValueDistribution();
        }
        return this.RValueDistribution;
    }

    public int[] getBValueDistribution() {
        if(this.BValueDistribution == null) {
            setBValueDistribution();
        }
        return this.RValueDistribution;
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