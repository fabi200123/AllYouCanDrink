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

    public BarcoolList(String name, String details, String category) {
        this.name = name;
        this.details = details;
        this.category = category;
    }

    public BarcoolList() {
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

}
