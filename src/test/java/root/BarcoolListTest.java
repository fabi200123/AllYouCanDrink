package root;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BarcoolListTest {

    @BeforeEach
    void setUp() throws Exception {
        MenuFileSystemService.APPLICATION_FOLDER = ".test-barcool";
        FileUtils.cleanDirectory(MenuFileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        GenericItemController.initDatabaseForBarcool();
    }

    @Test
    void testAddBarcoolInDatabase(){
        BarcoolList.addBarcool("J&B Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");
        assertThat(GenericItemController.getAllBarcools()).isNotEmpty();
        assertThat(GenericItemController.getAllBarcools()).size().isEqualTo(1);
    }

    @Test
    void testAddBarcoolElementInDatabase(){
        BarcoolList alcohol = new BarcoolList("J&B Whiskey", "50 ml / 100 ml, $5 - $8, shot de whiskey", "drink");
        BarcoolList.addBarcoolElement(alcohol);
        assertThat(GenericItemController.getAllBarcools()).isNotEmpty();
        assertThat(GenericItemController.getAllBarcools()).size().isEqualTo(1);
    }



    @AfterEach
    void tearDown() {
        GenericItemController.closeDatabaseForBarcool();
    }
}