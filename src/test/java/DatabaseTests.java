import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(GlobalTests.class)
public class DatabaseTests {

    @Test
    public void isConnectionWithTheDatabaseEstablished(){
        Database db = Database.getInstance();
        assertNotNull(db);
    }

}
