import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {

        Database database = Database.getInstance();
        MongoCollection<Document> collection = database.getCollection("pol-eng");

    }

}
