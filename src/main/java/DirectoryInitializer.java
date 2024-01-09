 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryInitializer {
    

    
    public void createDirectoryIfNotExists(String dirName) throws IOException {
        Path path = Paths.get(dirName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}