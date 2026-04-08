
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class clean {

    public static void main(String[] args) {
        // Change this to your project root directory
        Path rootDir = Paths.get(".");

        try (Stream<Path> paths = Files.walk(rootDir)) {
            paths
                .filter(path -> path.toString().endsWith(".class"))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        System.out.println("Deleted: " + path);
                    } catch (IOException e) {
                        System.err.println("Failed to delete: " + path);
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
