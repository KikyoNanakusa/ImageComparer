import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckPath {
    public boolean isValidPath(String path) {
        try {
            Path p = Paths.get(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
