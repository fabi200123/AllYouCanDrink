package root;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static root.MenuFileSystemService.getPathToFile;

public class GenericItemController {

    @FXML
    private Label label;

    public Label getLabel() {
        return label;
    }

    public void setLabel(String content) {
        label.setText(content);
    }

    private static ObjectRepository<BarcoolList> userRepository2;

    public static void initDatabaseForBarcool() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("barcool.db").toFile())
                .openOrCreate("admin", "admin");

        userRepository2 = database.getRepository(BarcoolList.class);
    }
}
