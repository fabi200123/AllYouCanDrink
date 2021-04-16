package root;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ComboBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    public void startMenu() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Meniu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleRegisterAction(){
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");

        } catch (ExceptionUsernameExists e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void handleLoginAction() throws Exception {
        try {
            UserService.checkUserDoesNotExist(usernameField.getText());
            startMenu();

        } catch (ExceptionUsernameDoesNotExist e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
