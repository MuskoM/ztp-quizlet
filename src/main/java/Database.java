import Flashcards.Flashcard;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import java.util.ArrayList;
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
        System.out.println(uriString);
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

    public List<String> getCollectionsNames(){
        List<String> arrayList = new ArrayList<>();
        MongoIterable<String> collNames = database.listCollectionNames();
        collNames.forEach(arrayList::add);
        return arrayList;
    }

    public void editFlashcard(String searchField, String searchString, String data, String collection){

        MongoCollection<Document> col = database.getCollection(collection);

        Bson filter = eq(searchField, searchString);
        Bson updateOperation = set(searchField,data);
        UpdateResult updateResult = col.updateOne(filter,updateOperation);
        System.out.println(updateResult);

    }

    public void addCollection(String collectionName){
        database.createCollection(collectionName);
    }

    public void removeCollection(String collectionName){
        MongoCollection mg = this.database.getCollection(collectionName);
        mg.drop();
    }

    public void addFlashcard(String languageWord, String translatedWord, String collection){
        MongoCollection mg = this.database.getCollection(collection);
        Document testDoc = new Document("languageWord",languageWord).append("translatedWord",translatedWord);
        mg.insertOne(testDoc);
    }

    public void removeFlashcard(String languageWord, String collection){
        MongoCollection mg = this.database.getCollection(collection);
        Bson filter = eq("languageWord", languageWord);
        mg.deleteOne(filter);
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
