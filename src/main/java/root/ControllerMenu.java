package root;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import static org.dizitart.no2.objects.filters.ObjectFilters.text;
import static root.GenericItemController.userRepository2;
import static root.UserService.userRepository;

public class ControllerMenu {

    private Parent root;
    @FXML
    private Button logout1, logout2, edit;


    @FXML
    private ListView<String> listViewLeft;
    @FXML
    private ListView<String> listViewRight;

    public void logout(ActionEvent event) throws Exception {
        Stage stage4 = new Stage();
        if(event.getSource() == logout1) {
            stage4 = (Stage) logout1.getScene().getWindow();
            stage4.setResizable(false);
            root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        }
        if(event.getSource() == logout2) {
            stage4 = (Stage) logout2.getScene().getWindow();
            stage4.setResizable(false);
            root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        }
        Scene scene4 = new Scene(root);
        stage4.setScene(scene4);
        stage4.show();
        GenericItemController.closeDatabaseForBarcool();
        UserService.initDatabase();
    }

    public void handleListSelect(){
        String selection = listViewLeft.getSelectionModel().getSelectedItem();
        String orice;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/generic_item.fxml"));
            Parent rootGeneric = fxmlLoader.load();
            GenericItemController controller =  fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle(selection);
            for (BarcoolList t : barcoollist) {
                if(selection.equals(t.getName())){
                    orice = selection + "\n" + t.getDetails();
                    controller.setLabel(orice);
                }
            }
            stage.setScene(new Scene(rootGeneric, 400, 400));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleListSelect2(){
        String selection = listViewRight.getSelectionModel().getSelectedItem();
        String orice;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/generic_item.fxml"));
            Parent rootGeneric = fxmlLoader.load();
            GenericItemController controller =  fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle(selection);
            for (BarcoolList t : bars) {
                if(selection.equals(t.getName())){
                    orice = selection + "\n" + t.getDetails();
                    controller.setLabel(orice);
                }
            }
            stage.setScene(new Scene(rootGeneric, 400, 400));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> managers = new ArrayList<String>();
    private ArrayList<BarcoolList> barcoollist = new ArrayList<BarcoolList>();
    private ArrayList<BarcoolList> bars = new ArrayList<BarcoolList>();

    public void updateBars(){
        GenericItemController.closeDatabaseForBarcool();
        UserService.initDatabase();
        managers.clear();
        for (User t : userRepository.find()) {
            if (Objects.equals(t.getRole(), "Manager"))
                managers.add(t.getUsername());
        }
        UserService.closeDatabase();
        GenericItemController.initDatabaseForBarcool();

        for(String s : managers){
            int ok = 0;
            for(BarcoolList t : bars){
                if(t.getName().equals(s)){
                    ok = 1; break;
                }
            }
            if (ok == 0) BarcoolList.addBarcool(s, "", "bar");
        }

    }
    static String APPLICATION_FOLDER = ".request";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile3(String... path) {
        return getApplicationHomeFolder3().resolve(Paths.get(".", path));
    }
    public static Path getApplicationHomeFolder3() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    protected static ObjectRepository<Request> userRepository3;
    static Nitrite database3;

    public static void initDatabaseForRequest() {
        UserService.closeDatabase();
        database3 = Nitrite.builder()
                .filePath(getPathToFile3("request.db").toFile())
                .openOrCreate("admin", "admin");
        userRepository3 = database3.getRepository(Request.class);
    }

    public static void closeDatabaseForRequests() {
        database3.close();
    }

    public void handleRequest(){
        Button buttie = new Button();
        TextField txt = new TextField();
        Label labi = new Label("Write your request in a single line and press Send!");
        buttie.setText("Send Request");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(buttie);
        buttie.setTranslateX(150);
        buttie.setTranslateY(150);
        labi.setTranslateY(-100);
        txt.setId("text");
        Scene secondScene = new Scene(secondaryLayout, 600, 400);
        Stage newWindow = new Stage();
        newWindow.setTitle("Drink Request");
        newWindow.setScene(secondScene);
        newWindow.show();
        buttie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String requ = txt.getText();
                GenericItemController.closeDatabaseForBarcool();
                initDatabaseForRequest();
                try {
                    userRepository3.insert(new Request(requ));
                    closeDatabaseForRequests();
                    GenericItemController.initDatabaseForBarcool();
                    newWindow.close();
                    StackPane secondaryLayout1 = new StackPane();
                    Label textie = new Label("Request sent succesfully!");
                    textie.setId("label");
                    secondaryLayout1.getChildren().add(textie);
                    Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                    Stage newWindow1 = new Stage();
                    newWindow1.setTitle("Confirm Request");
                    newWindow1.setScene(secondScene1);
                    newWindow1.show();
                }
                catch (Exception e){
                    closeDatabaseForRequests();
                    GenericItemController.initDatabaseForBarcool();
                    newWindow.close();
                    StackPane secondaryLayout1 = new StackPane();
                    Label textie = new Label("Request already exists!");
                    textie.setId("label1");
                    secondaryLayout1.getChildren().add(textie);
                    Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                    Stage newWindow1 = new Stage();
                    newWindow1.setTitle("Request Failure");
                    newWindow1.setScene(secondScene1);
                    newWindow1.show();
                }
            }
        });
        secondaryLayout.getChildren().add(txt);
        secondaryLayout.getChildren().add(labi);

    }

    public void initialize(){
        GenericItemController.initDatabaseForBarcool();
        /*BarcoolList.addBarcool("Absolut Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("J&B Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");
        BarcoolList.addBarcool("Don Pablo Tequila", "50 ml / 100 ml, $4 - $7, shot de tequila cu sare si lamaie", "drink");
        BarcoolList.addBarcool("Ursus Retro Beer", "330 ml / 500 ml, $2 - $7, la sticla / doza", "drink");
        BarcoolList.addBarcool("Stalinskaya Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("Finlandia Vodka", "50 ml / 100 ml, $3 - $5, shot de vodka", "drink");
        BarcoolList.addBarcool("Jack Daniels Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");*/

        barcoollist.clear();
        bars.clear();
        for (BarcoolList t : userRepository2.find()) {
            if(t.getCategory().equals("drink"))
                barcoollist.add(t);
            else if(t.getCategory().equals("bar"))
                bars.add(t);
        }
        updateBars();
        bars.clear();
        for (BarcoolList t : userRepository2.find()) {
            if(t.getCategory().equals("bar"))
                bars.add(t);
        }
        Collections.sort(barcoollist, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        listViewLeft.getItems().clear();
        for (BarcoolList t : barcoollist) {
            listViewLeft.getItems().add(t.getName());
        }
        Collections.sort(bars, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        listViewRight.getItems().clear();
        for (BarcoolList t : bars) {
            listViewRight.getItems().add(t.getName());
        }


    }

    public Boolean verifyInDatabase(String s){
        for(BarcoolList l : userRepository2.find()){
            if (l.getName().equals(s) && l.getCategory().equals("drink")) return true;
        }
        return false;
    }

    public void handleEditButton(){
        Button add = new Button();
        Button delete = new Button();
        TextField chestie = new TextField();
        Label labi = new Label("Write down the name of the drink you want to add/delete!");
        add.setText("Add Drink");
        delete.setText("Delete Drink");
        add.setId("add");
        delete.setId("delete");
        chestie.setId("chestie");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(add);
        secondaryLayout.getChildren().add(delete);
        secondaryLayout.getChildren().add(labi);
        secondaryLayout.getChildren().add(chestie);
        add.setTranslateX(150);
        add.setTranslateY(150);
        delete.setTranslateX(-150);
        delete.setTranslateY(150);
        labi.setTranslateX(0);
        labi.setTranslateY(-100);
        Scene secondScene = new Scene(secondaryLayout, 600, 400);
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Menu");
        newWindow.setScene(secondScene);
        newWindow.show();
        String usernom = Controller.username();
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int flag = 0;
                String drink = chestie.getText();
                if (!verifyInDatabase(drink)){
                    flag = 1;
                }
                if(flag == 0) {
                    BarcoolList aux = null;
                    for (BarcoolList t : userRepository2.find()) {
                        if (t.getName().equals(usernom)) {
                            String G[] = t.getDetails().split("\n");
                            for(String strtok : G){
                                if(strtok.equals(drink)){
                                    flag = 2;
                                    break;
                                }
                            }
                            if(flag == 2) {
                                newWindow.close();
                                StackPane secondaryLayout1 = new StackPane();
                                Label textie = new Label("Drink already exists");
                                textie.setId("textie");
                                secondaryLayout1.getChildren().add(textie);
                                Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                                Stage newWindow1 = new Stage();
                                newWindow1.setTitle("Add Failure");
                                newWindow1.setScene(secondScene1);
                                newWindow1.show();
                                break;
                            }
                            else {
                                aux = new BarcoolList(t.getName(), t.getDetails() + "\n" + drink, t.getCategory(), t.getLike(), t.getDislike());
                                userRepository2.remove(eq("name", aux.getName()));
                                break;
                            }
                        }
                    }
                    if(flag != 2) {
                        BarcoolList.addBarcoolElement(aux);
                        newWindow.close();
                        StackPane secondaryLayout1 = new StackPane();
                        Label textie = new Label("Drink added successfully");
                        textie.setId("textie1");
                        secondaryLayout1.getChildren().add(textie);
                        Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                        Stage newWindow1 = new Stage();
                        newWindow1.setTitle("Confirm Add");
                        newWindow1.setScene(secondScene1);
                        newWindow1.show();
                    }
                }
                else{
                    newWindow.close();
                    StackPane secondaryLayout1 = new StackPane();
                    Label textie = new Label("Drink doesn't exist, make a request for admin");
                    textie.setId("textie2");
                    secondaryLayout1.getChildren().add(textie);
                    Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                    Stage newWindow1 = new Stage();
                    newWindow1.setTitle("Add Failure");
                    newWindow1.setScene(secondScene1);
                    newWindow1.show();
                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String drink = chestie.getText();
                BarcoolList aux = null;
                int flag = 0;
                for(BarcoolList t : userRepository2.find()){
                    if(t.getName().equals(usernom)){
                        String ss[]= t.getDetails().split("\n");
                        String newDetails = "";
                        for(String s : ss){
                            if(drink.equals(s)) flag = 1;
                            else newDetails = newDetails + "\n" + s;
                        }
                        if (flag == 0){
                            break;
                        }
                        aux = new BarcoolList(t.getName(), newDetails, t.getCategory(), t.getLike(), t.getDislike());
                        userRepository2.remove(eq("name", aux.getName()));
                        break;
                    }
                }
                if (flag == 1){
                    BarcoolList.addBarcoolElement(aux);
                    newWindow.close();
                    StackPane secondaryLayout1 = new StackPane();
                    Label textie = new Label("Drink deleted successfully");
                    textie.setId("textie3");
                    secondaryLayout1.getChildren().add(textie);
                    Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                    Stage newWindow1 = new Stage();
                    newWindow1.setTitle("Confirm Delete");
                    newWindow1.setScene(secondScene1);
                    newWindow1.show();
                }
                else {
                    newWindow.close();
                    StackPane secondaryLayout1 = new StackPane();
                    Label textie = new Label("Drink not found");
                    textie.setId("textie4");
                    secondaryLayout1.getChildren().add(textie);
                    Scene secondScene1 = new Scene(secondaryLayout1, 260, 50);
                    Stage newWindow1 = new Stage();
                    newWindow1.setTitle("Delete Failure");
                    newWindow1.setScene(secondScene1);
                    newWindow1.show();
                }
            }
        });
    }
}