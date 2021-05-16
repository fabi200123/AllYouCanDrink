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

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ClientMenuTest {

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
        MenuFileSystemService.APPLICATION_FOLDER = ".test-barcool";
        FileUtils.cleanDirectory(MenuFileSystemService.getPathToFile().toFile());
        GenericItemController.initDatabaseForBarcool();
        BarcoolList.addBarcool("Absolut Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("J&B Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");
        BarcoolList.addBarcool("Don Pablo Tequila", "50 ml / 100 ml, $4 - $7, shot de tequila cu sare si lamaie", "drink");
        BarcoolList.addBarcool("Ursus Retro Beer", "330 ml / 500 ml, $2 - $7, la sticla / doza", "drink");
        BarcoolList.addBarcool("Stalinskaya Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("Finlandia Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("Jack Daniels Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");
        GenericItemController.closeDatabaseForBarcool();
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
        Mishu.clickOn("Logout");
        Mishu.clickOn("#username");
        Mishu.write("BarulSatului");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Manager");
        Mishu.clickOn("#register");
        Mishu.clickOn("#login");
        Mishu.clickOn("Logout");
        Mishu.clickOn("#username");
        Mishu.write("Maricica");
        Mishu.clickOn("#pass");
        Mishu.write("123");
        Mishu.clickOn("#role");
        Mishu.clickOn("Client");
        Mishu.clickOn("#register");
        Mishu.clickOn("#login");
        Mishu.clickOn("Absolut Vodka");
        Mishu.clickOn("#likebutton");
        assertThat(Mishu.lookup("#likebutton").queryButton()).hasStyle("-fx-background-color:LightGreen; ");
        Mishu.clickOn("#dislikebutton");
        assertThat(Mishu.lookup("#dislikebutton").queryButton()).doesNotHaveStyle("-fx-background-color: #f51e1e; ");
        Mishu.closeCurrentWindow();
        Mishu.clickOn("#listViewLeft");
        Mishu.clickOn("#dislikebutton");
        assertThat(Mishu.lookup("#dislikebutton").queryButton()).hasStyle("-fx-background-color: #f51e1e; ");
        Mishu.clickOn("#likebutton");
        assertThat(Mishu.lookup("#likebutton").queryButton()).doesNotHaveStyle("-fx-background-color:LightGreen; ");
        Mishu.closeCurrentWindow();
        Mishu.clickOn("Mishu`sBar");
        Mishu.clickOn("#dislikebutton");
        assertThat(Mishu.lookup("#dislikebutton").queryButton()).hasStyle("-fx-background-color: #f51e1e; ");
        Mishu.clickOn("#likebutton");
        assertThat(Mishu.lookup("#likebutton").queryButton()).doesNotHaveStyle("-fx-background-color:LightGreen; ");
        Mishu.closeCurrentWindow();
        Mishu.clickOn("BarulSatului");
        Mishu.clickOn("#likebutton");
        assertThat(Mishu.lookup("#likebutton").queryButton()).hasStyle("-fx-background-color:LightGreen; ");
        Mishu.clickOn("#dislikebutton");
        assertThat(Mishu.lookup("#dislikebutton").queryButton()).doesNotHaveStyle("-fx-background-color: #f51e1e; ");
        Mishu.closeCurrentWindow();
        Mishu.clickOn("Logout");
        Mishu.closeCurrentWindow();
    }



    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }
}