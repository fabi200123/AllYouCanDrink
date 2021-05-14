package root;

import org.apache.commons.io.FileUtils;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import root.ExceptionUsernameExists;
class UserServiceTest {

    public static final String Client = "Client";
    public static final String parola = "test";
    public static final String Manager = "manager";
    public static final String role_c = "client";
    public static final String role_m = "manager";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
        UserService.initDatabase();
    }

    @Test
    void testDatabaseIsInitializedAndNoUserinsideit() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    void testIfUserIsAddedCorectly() throws ExceptionUsernameExists {
        UserService.addUser(Client,parola, role_c);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(Client);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(Client, parola));
        UserService.addUser(Manager, parola, role_m);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        user = UserService.getAllUsers().get(1);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(Manager);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(Manager, parola));
    }

    @Test
    void testUsernamealreadyExists() {
        assertThrows(ExceptionUsernameExists.class, () -> {
            UserService.addUser(Client,parola, role_c);
            UserService.addUser(Client,parola, role_c);
            UserService.addUser(Manager,parola, role_m);
            UserService.addUser(Manager,parola, role_m);
        });
    }

    @Test
    void testIfPassowordIsCorectly()throws ExceptionUsernameExists{
        UserService.addUser(Client,parola, role_c);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        assertThat(UserService.checkPass(Client)).isNotEqualTo("You Dumb! ;)");
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}