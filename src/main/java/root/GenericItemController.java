package root;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GenericItemController {

    @FXML
    private Label label;

    public Label getLabel() {
        return label;
    }

    public void setLabel(String content) {
        label.setText(content);
    }
}
