package root;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static root.FileSystemService.getPathToFile;


public class UserService {

    protected static ObjectRepository<User> userRepository;

    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("registration.db").toFile())
                .openOrCreate("admin", "admin");
        userRepository = database.getRepository(User.class);
    }

    public static void closeDatabase() {
        database.close();
    }

    public static void addUser(String username, String password, String role) throws ExceptionUsernameExists {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static String encoder(String username, String password){
        return encodePassword(username, password);
    }

    public static String checkRole(String username) {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return user.getRole();
        }
        return "You dumb! ;)";
    }

    public static String checkPass(String username) {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return user.getPassword();
        }
        return "You dumb! ;)";
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws ExceptionUsernameExists {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new ExceptionUsernameExists(username);
        }
    }

    public static void checkUserDoesNotExist(String username) throws ExceptionUsernameDoesNotExist {
        int ok = 0;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                ok = 1;
                break;
            }
        }
        if (ok == 0){
            throw new ExceptionUsernameDoesNotExist(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try { //SHA-512
            md = MessageDigest.getInstance("SHA-512"); //Algoritm super complicat de codificare a parolei, garantat neluat de la profi
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Toata cryptarea a fost o minciuna!");
        }
        return md;
    }


}