package root;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

import static root.UserService.initDatabase;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        initDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        primaryStage.setTitle("AllYouCanDrink");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}