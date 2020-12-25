import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {

    private static Database instance;
    private MongoDatabase database;

    private final String uriString = "mongodb+srv://nvi0:Deskjet5150@cluster0.fgs4p.mongodb.net/ztp-quizlet?retryWrites=true&w=majority";


    private Database(){
        MongoClient client = MongoClients.create(uriString);
        this.database = client.getDatabase("ztp-quizlet");
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public MongoCollection getCollection(String collectionName){
        return this.database.getCollection(collectionName);
    }

}

 /*
   //
    Database database = Database.getInstance();
    MongoCollection<Document> collection = database.getCollection("pol-eng");

   //Dodaj do kolekcji
    Document testDoc = new Document("pies","dog").append("kot","cat");
    Document insideDoc = new Document("typ","zwierzęta");
    testDoc.put("meta-data",insideDoc);
    collection.insertOne(testDoc);

   //Pobierz z kolekcji


   //Usuń z kolekcji

 */
