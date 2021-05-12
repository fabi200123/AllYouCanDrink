package root;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static root.FileSystemService.getPathToFile;


public class BarcoolList {

    @Id
    private String name;
    private String details;
    private String category;
    private int like;
    private int dislike;

    public BarcoolList(String name, String details, String category) {
        this.name = name;
        this.details = details;
        this.category = category;
        this.like = 0;
        this.dislike = 0;
    }

    public BarcoolList(String name, String details, String category, int like, int dislike) {
        this.name = name;
        this.details = details;
        this.category = category;
        this.like = like;
        this.dislike = dislike;
    }

    public BarcoolList(BarcoolList barcool) {
        this.name = barcool.getName();
        this.details = barcool.getDetails();
        this.category = barcool.getCategory();
        this.like = barcool.getLike();
        this.dislike = barcool.getDislike();
    }

    public BarcoolList() {
    }

    public static void addBarcool(String name, String details, String category){
        root.GenericItemController.userRepository2.insert(new BarcoolList(name, details, category));
    }

    public static void addBarcoolElement(BarcoolList element){
        root.GenericItemController.userRepository2.insert(new BarcoolList(element));
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
            this.category = category;
    }

    public int getLike() {
        return like;
    }
    public void setLike() { like = like + 1; }

    public int getDislike() { return dislike; }
    public void setDislike() { dislike = dislike + 1; }

}
