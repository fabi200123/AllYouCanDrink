package root;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.validation.constraints.Null;
import java.io.IOException;

public class ControllerMenu {

    private Parent root;
    @FXML
    private Button logout1, logout2;


    @FXML
    private ListView<String> listViewLeft;

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

    public void handleListSelect(){
        String selection = listViewLeft.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/generic_item.fxml"));
            Parent rootGeneric = fxmlLoader.load();
            GenericItemController controller =  fxmlLoader.getController();
            controller.setLabel(selection);
            Stage stage = new Stage();
            stage.setScene(new Scene(rootGeneric, 400, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        listViewLeft.getItems().addAll("item 1", "item 2", "item 3", "item 4");
    }
}

