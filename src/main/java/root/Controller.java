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
        Scene scene = new Scene(fxmlLoader.load(), 993, 692);
        Stage stage = new Stage();
        stage.setTitle("Meniu Client");
        stage.setScene(scene);
        stage.show();
    }

    public void startMenu2() throws Exception {
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/fxml/menu_2.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 993, 692);
        Stage stage2 = new Stage();
        stage2.setTitle("Meniu Manager");
        stage2.setScene(scene2);
        stage2.show();
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
            String string = UserService.checkPass(usernameField.getText());
            if (string.equals(UserService.encoder(usernameField.getText(), passwordField.getText()))){
                string = UserService.checkRole(usernameField.getText());
                if(string.equals(role.getValue()))
                    startMenu();
                else registrationMessage.setText("Wrong role for this username");
            }
            else  registrationMessage.setText("Wrong password for this username");


        } catch (ExceptionUsernameDoesNotExist e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
