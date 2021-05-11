package root;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.validation.constraints.Null;

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
    private Button login1;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    public void startMenu() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 875, 657);
        Stage stage = new Stage();
        stage = (Stage) login1.getScene().getWindow();
        stage.setTitle("Meniu Client");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void startMenu2() throws Exception {
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/fxml/menu_2.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 875, 657);
        Stage stage2 = new Stage();
        stage2 = (Stage) login1.getScene().getWindow();
        stage2.setTitle("Meniu Manager");
        stage2.setScene(scene2);
        stage2.setResizable(false);
        stage2.show();
    }

    @FXML
    public void handleRegisterAction(){
        try {
            if((String) role.getValue() != "Manager" && (String) role.getValue() != "Client"){
                registrationMessage.setText("Dummy, Dummy, you forgot your role!");
            }
            else {
                UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                registrationMessage.setText("Account created successfully!");
            }
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
                    if((String)role.getValue() == "Client"){
                        startMenu();
                    }
                    else {
                        startMenu2();
                    }
                else registrationMessage.setText("Wrong role for this username");
            }
            else  registrationMessage.setText("Wrong password for this username");


        } catch (ExceptionUsernameDoesNotExist e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}