package root;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class GenericItemControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.closeDatabase();
        ControllerMenu.APPLICATION_FOLDER = ".test-request";
        FileUtils.cleanDirectory(ControllerMenu.getApplicationHomeFolder3().toFile());
        ControllerMenu.initDatabaseForRequest();
        ControllerMenu.closeDatabaseForRequests();
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
    void testMeniuManager(FxRobot Mishu) throws IOException {
        Mishu.clickOn("#username");
        Mishu.write("Mishu`sBar");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Manager");
        Mishu.clickOn("#register");
        Mishu.clickOn("#login");
        Mishu.clickOn("#request");
        Mishu.clickOn("#text");
        Mishu.write("Timisoareana Beer");
        Mishu.clickOn("Send Request");
        assertThat(Mishu.lookup("#label").queryLabeled()).hasText("Request sent succesfully!");
        Mishu.clickOn("#request");
        Mishu.clickOn("#text");
        Mishu.write("Timisoareana Beer");
        Mishu.clickOn("Send Request");
        assertThat(Mishu.lookup("#label1").queryLabeled()).hasText("Request already exists!");
        Mishu.clickOn("Logout");
    }



    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}