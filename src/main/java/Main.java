package main.java;
class Main {
    public static void main(String[] args) {
        IImageScraper bookwalkerScraper = new BookWalkerScraper("https://bookwalker.jp/new/?qsto=st2");
        ImageAnalyzer imageAnalyzer = new ImageAnalyzer(bookwalkerScraper.getImagePathes());
    }

}