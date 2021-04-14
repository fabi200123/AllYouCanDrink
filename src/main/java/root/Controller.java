package root;

/*public class Controller {
}*/

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.text.Text;

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

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (ExceptionUsernameExists e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
