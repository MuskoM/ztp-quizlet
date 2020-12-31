import FlashcardCollection.FlashcardCollection;
import Flashcards.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {

        Database database = Database.getInstance();
        FlashcardCollection fc = new FlashcardCollection(database);
        MongoCollection<Document> collection = database.getCollection("pol-eng");
        FindIterable<Document> iterable;
        List<Flashcard> words = new ArrayList<>();
        iterable = collection.find(new Document());
        iterable.forEach(document -> {
            words.add(new Flashcard(document.get("languageWord").toString(),
                    document.get("translatedWord").toString()));
        });

        for(int i = 0; i < words.size(); i++){
            switch (i%4){
                case 0:
                    Flashcard lvl_1 = new Level1Flashcard(words.get(i));
                    lvl_1.viewFlashcard("");
                    break;
                case 1:
                    Flashcard lvl_2 = new Level2Flashcard(words.get(i));
                    lvl_2.viewFlashcard("");
                    break;
                case 2:
                    Flashcard lvl_3 = new Level3Flashcard(words.get(i));
                    lvl_3.viewFlashcard("");
                    break;
                case 3:
                    Flashcard lvl_4 = new Level4Flashcard(words.get(i));
                    lvl_4.viewFlashcard("");
                    break;
            }
        }
        }
    }
