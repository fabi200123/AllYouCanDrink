package root;

public class ExceptionUsernameExists extends Exception {

    private String username;

    public ExceptionUsernameExists(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
