package root;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.validation.constraints.Null;

public class ControllerMenu {

    private Parent root;
    @FXML
    private Button logout1, logout2;
    public void logout(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        Stage stage4 = new Stage();
        if(event.getSource() == logout1) {
            stage4 = (Stage) logout1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        }
        if(event.getSource() == logout2) {
            stage4 = (Stage) logout2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        }
        Scene scene4 = new Scene(root);
        stage4.setScene(scene4);
        stage4.show();
    }
}

