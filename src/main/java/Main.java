import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.xml.crypto.Data;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        Database database = Database.getInstance();
        MongoCollection<Document> collection = database.getCollection("pol-eng");
        FindIterable<Document> iterable = collection.find(new Document());
        iterable.forEach(document -> System.out.println(document.toJson()));

    }

}
