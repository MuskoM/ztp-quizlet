import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseTests {

    @Test
    public void isConnectionWithTheDatabaseEstablished(){
        Database db = Database.getInstance();
        assertNotNull(db);
    }

}
