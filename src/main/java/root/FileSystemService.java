package root;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    public static String APPLICATION_FOLDER = ".registration";
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static Path getPathToFile(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

}


