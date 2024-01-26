 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryInitializer {
    public DirectoryInitializer() {
        try {
            createDirectoryIfNotExists(DirectoryPathData.USER_PREPARED_DATASET_PATH);
            createDirectoryIfNotExists(DirectoryPathData.DOWNLOADS_PATH);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void createDirectoryIfNotExists(String dirName) throws IOException {
        Path path = Paths.get(dirName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}