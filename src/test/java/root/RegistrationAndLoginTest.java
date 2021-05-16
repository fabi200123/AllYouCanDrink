package root;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationAndLoginTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        primaryStage.setTitle("AllYouCanDrink");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Test
    void testRegistrationAndLogin(FxRobot Mishu) throws InterruptedException {
        Mishu.clickOn("#username");
        Mishu.write("Viorel");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Client");
        Mishu.clickOn("#register");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        Mishu.clickOn("#register");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("An account with the username Viorel already exists!");
        Mishu.clickOn("#login");
        Mishu.clickOn("Logout");

        Mishu.clickOn("#username");
        Mishu.write("MishuBar");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Manager");
        Mishu.clickOn("#register");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        Mishu.clickOn("#register");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("An account with the username MishuBar already exists!");
        Mishu.clickOn("#login");
        Mishu.clickOn("Logout");

        Mishu.clickOn("#username");
        Mishu.write("MishuBar2");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Manager");
        Mishu.clickOn("#login");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Funny though! Nobody uses the username: MishuBar2!");
        Mishu.clickOn("#username");
        Mishu.eraseText(9);
        Mishu.write("MishuBar");
        Mishu.clickOn("#role");
        Mishu.clickOn("Client");
        Mishu.clickOn("#login");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Wrong role for this username");
        Mishu.clickOn("#role");
        Mishu.clickOn("Manager");
        Mishu.clickOn("#login");
        Mishu.clickOn("Logout");
        Mishu.clickOn("#username");
        Mishu.write("MishuBar3");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("#register");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Dummy, Dummy, you forgot your role!");
        Mishu.clickOn("#role");
        Mishu.clickOn("Client");
        Mishu.clickOn("#login");
        assertThat(Mishu.lookup("#registrationMessage").queryText()).hasText("Funny though! Nobody uses the username: MishuBar3!");

    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}