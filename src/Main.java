class Main {
    public static void main(String[] args) {
        // ArgsChecker.Check(args);
        IImageScraper bookwalkerScraper = new BookWalkerScraper("https://bookwalker.jp/new/?qsto=st2");
        // System.out.println(bookwalkerScraper.getImagePathes());        
    }

}