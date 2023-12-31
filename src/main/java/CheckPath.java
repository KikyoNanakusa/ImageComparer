package main.java;
import java.nio.file.Paths;

public class CheckPath {
    public boolean isValidPath(String path) {
        try {
            Paths.get(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
