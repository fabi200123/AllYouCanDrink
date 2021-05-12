package root;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static root.MenuFileSystemService.getPathToFile;

public class GenericItemController {

    @FXML
    private Label label;
    @FXML
    private Button likebutton, dislikebutton;

    private int flag = 0;

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

    public void handleLike(){
        if(flag == 0) {
            likebutton.setStyle("-fx-background-color: LightGreen; ");
            flag = 1;
        }
        String s = label.getText();
        String ss[]= s.split("\n");
        s = ss[0];
        BarcoolList aux = null;
        for(BarcoolList t : userRepository2.find()){
            if(t.getName().equals(s)){
                aux = new BarcoolList(t.getName(), t.getDetails(), t.getCategory(), t.getLike() + 1, t.getDislike());
                userRepository2.remove(eq("name", aux.getName()));
                break;
            }
        }
        BarcoolList.addBarcoolElement(aux);
    }

    public void handleDislike(){
        if(flag == 0) {
            dislikebutton.setStyle("-fx-background-color: #f51e1e; ");
            flag = 1;
        }
        String s = label.getText();
        String ss[]= s.split("\n");
        s = ss[0];
        BarcoolList aux = null;
        for(BarcoolList t : userRepository2.find()){
            if(t.getName().equals(s)){
                aux = new BarcoolList(t.getName(), t.getDetails(), t.getCategory(), t.getLike(), t.getDislike() + 1);
                userRepository2.remove(eq("name", aux.getName()));
                break;
            }
        }
        BarcoolList.addBarcoolElement(aux);
    }
}
