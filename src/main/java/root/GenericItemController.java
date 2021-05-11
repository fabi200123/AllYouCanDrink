package root;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
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

    protected static ObjectRepository<BarcoolList> userRepository2;

    private static Nitrite database;

    public static void initDatabaseForBarcool() {
        UserService.closeDatabase();
        database = Nitrite.builder()
                .filePath(getPathToFile("barcool.db").toFile())
                .openOrCreate("admin", "admin");
        userRepository2 = database.getRepository(BarcoolList.class);
    }

    public static void closeDatabaseForBarcool(){
        database.close();
    }
}
