import Flashcards.Flashcard;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {

        Database database = Database.getInstance();
        MongoCollection<Document> collection = database.getCollection("pol-eng");
        FindIterable<Document> iterable;
        List<Flashcard> words = new ArrayList<>();
        iterable = collection.find(eq("languageWord","pen"));
        iterable.forEach(document -> {
            words.add(new Flashcard(document.get("languageWord").toString(),
                    document.get("translatedWord").toString()));
        });

        words.get(0).viewFlashcard("bbbr");
    }

}
