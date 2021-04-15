package root;

public class ExceptionUsernameDoesNotExist extends Exception {

    private String username;

    public ExceptionUsernameDoesNotExist(String username) {
        super(String.format("Funny though! Nobody uses the username: %s!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}