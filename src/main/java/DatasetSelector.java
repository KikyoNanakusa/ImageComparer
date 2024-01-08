import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The DatasetSelector class allows the user to select a dataset and retrieves the corresponding image
 * paths.
 */
public class DatasetSelector {
    private InputHandler inputHandler;
    private final int MIN_INUPT = 1;
    private final int MAX_INPUT = 3;
    private final String BOOKWALKER_IMAGE_DIRECTRY = "downloads/img/BookWalker";
    private final String USER_IMAGE_DIRECTRY = "UserPreparedData";
    private ArrayList<String> imagePaths;


    public DatasetSelector() {
        inputHandler = new InputHandler();
        imagePaths = new ArrayList<String>();
        printInstructions();
    }
    
    private void printInstructions() {
        System.out.println("Choose Dataset");
        System.out.println("1:Scrape newest 300 Mangas from BookWalker");
        System.out.println("2:Scrape newest 60 Mangas from BookWalker");
        System.out.println("3:Exsiting BookWalker images");
        System.out.println("4:Your images");
    }    

    private boolean validateUserInput(int input) {
        if (input < MIN_INUPT || input > MAX_INPUT) {
            System.out.println("Enter the appropriate value");
            printInstructions();
            return false;
        }
        return true;
    }

    private ArrayList<String> getAllFileInDirectry(String directryName) {
        Path dir = Paths.get(directryName); // ディレクトリのパスを指定
        ArrayList<String> paths = new ArrayList<>();

        try {
           Files.walk(dir)
               .filter(Files::isRegularFile) // ディレクトリ自体とサブディレクトリは除外
               .forEach(p -> paths.add(p.toString()));
        } catch (IOException e) {
           e.printStackTrace();
       }

       return paths;
    }

    /**
    * The function `selectDataset()` allows the user to select a dataset based on their input and performs
    * different actions accordingly.
    */
    public void selectDataset() {
        int input;
        do {
            input = inputHandler.getInput();
        } while (!validateUserInput(input));

        switch(input) {
            case 1:
                IImageScraper bookwalkerScraper = new BookWalkerScraper(BookWalkerURLs.newest300Mangas);
                imagePaths = bookwalkerScraper.getImagePathes();
                break;
            case 2:
                bookwalkerScraper = new BookWalkerScraper(BookWalkerURLs.newesy60Mangas);
                imagePaths = bookwalkerScraper.getImagePathes();
                break;
            case 3:
                imagePaths = getAllFileInDirectry(BOOKWALKER_IMAGE_DIRECTRY);
                break;
            case 4:
                imagePaths = getAllFileInDirectry(USER_IMAGE_DIRECTRY);
                break;
            default:
                throw new IllegalArgumentException("Invalid input");
        }
    }
    
    /**
    * The function returns an ArrayList of image paths.
    * 
    * @return An ArrayList of Strings containing image paths.
    */
    public ArrayList<String> getImagePaths() {
        return this.imagePaths;
    }

}

