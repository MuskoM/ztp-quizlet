import Flashcards.Flashcard;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Database template:
 * _id:ObjectId("5fe....."
 * languageWord: "apple"
 * translatedWord: "jabłko"
 */


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

    public List getOptionWords(String field_name){

        List <String> documents = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("pol-eng");
        FindIterable<Document> iterable;
        iterable = collection.find(new Document());
        iterable.forEach(document -> {
            documents.add(document.get(field_name).toString());
        });
        return  documents;
    }
}

 /*
   //
    Database database = Database.getInstance();
    MongoCollection<Document> collection = database.getCollection("pol-eng");

   //Dodaj do kolekcji
    Document testDoc = new Document("languageWord","dog").append("translatedWord","pies");
    Document insideDoc = new Document("typ","zwierzęta");
    testDoc.put("meta-data",insideDoc);
    collection.insertOne(testDoc);

    collection.insertMany(asList(Document.parse("{languageWord: 'pen', translatedWord: 'długopis'}"),
                Document.parse("{languageWord: 'cat', translatedWord: 'kot'}"),
                Document.parse("{languageWord: 'bucket', translatedWord: 'wiadro'}"),
                Document.parse("{languageWord: 'apple', translatedWord: 'jabłko'}"),
                Document.parse("{languageWord: 'case', translatedWord: 'przypadek'}"),
                Document.parse("{languageWord: 'computer', translatedWord: 'komputer'}")));

   //Pobierz z kolekcji
    FindIterable<Document> iterable = collection.find(new Document());

    iterable.forEach(document -> System.out.println(document.toJson()));

    iterable = collection.find(eq("languageWord","pen"));
        iterable.forEach(document -> {
            System.out.println(document);
        });

   //Usuń z kolekcji

 */
