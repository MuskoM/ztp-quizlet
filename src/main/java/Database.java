
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class Database {

    private static Database instance;
    private MongoDatabase database;


    private Database(){
        Dotenv env_vars = Dotenv.load();
        String uriString = "mongodb+srv://" +
                            env_vars.get("DATABASE_LOGIN") +":" +
                            env_vars.get("DATABASE_PASSWORD") + "@cluster0.fgs4p.mongodb.net/" +
                            env_vars.get("DATABASE_NAME")+"?retryWrites=true&w=majority";
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
    FindIterable<Document> iterable = collection.find(new Document());
    iterable.forEach(document -> System.out.println(document.toJson()));

   //Usuń z kolekcji

 */