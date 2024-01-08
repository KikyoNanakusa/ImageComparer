import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * The ImagePathFinder class is a Java class that finds and returns the absolute paths of all image
 * files (.jpg, .png, .gif) within a given directory.
 */
public class ImagePathFinder {

    private String directoryPath;

    public ImagePathFinder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * The function `getImagePaths()` returns a list of absolute file paths for all
     * image files (.jpg,
     * .png, .gif) in a given directory.
     * 
     * @return The method is returning an ArrayList of String objects, which
     *         represents the absolute paths
     *         of image files (with extensions .jpg, .png, or .gif) found in the
     *         specified directory.
     */
    public ArrayList<String> getImagePaths() {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid Path" + directoryPath);
        }

        FilenameFilter imageFilter = (dir, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif");

        File[] files = directory.listFiles(imageFilter);
        ArrayList<String> imagePaths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imagePaths.add(file.getAbsolutePath());
            }
        }

        return imagePaths;
    }
}
