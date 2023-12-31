package main.java;
import java.awt.image.BufferedImage;

class ColorData {
    private int[][] RGB;
    private int[][] R;
    private int[][] G;
    private int[][] B;
    private int[] RvalueDistribution;
    private int[] GvalueDistribution;
    private int[] BvalueDistribution;


    public ColorData(BufferedImage image) {
        initRGB(image);
    }

    private void initRGB(BufferedImage image) {
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

    private void setRvalueDistribution() {
        this.RvalueDistribution = new int[256];
        for (int[] rows : this.R) {
            for(int value : rows) {
                this.RvalueDistribution[value] += 1;
            }
        }
    }

    private void setGvalueDistribution() {
        this.GvalueDistribution = new int[256];
        for (int[] rows : this.G) {
            for(int value : rows) {
                this.GvalueDistribution[value] += 1;
            }
        }
    }

    private void setBvalueDistribution() {
        this.BvalueDistribution = new int[256];
        for (int[] rows : this.B) {
            for(int value : rows) {
                this.BvalueDistribution[value] += 1;
            }
        }
    }

    public int[] getRvalueDistribution() {
        if(this.RvalueDistribution == null) {
            setRvalueDistribution();
        }
        return this.RvalueDistribution;
    }

    public int[] getGvalueDistribution() {
        if(this.GvalueDistribution == null) {
            setGvalueDistribution();
        }
        return this.RvalueDistribution;
    }

    public int[] getBvalueDistribution() {
        if(this.BvalueDistribution == null) {
            setBvalueDistribution();
        }
        return this.RvalueDistribution;
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