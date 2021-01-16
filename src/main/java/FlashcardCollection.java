import Flashcards.Flashcard;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCollection {

    public static final String FIRST_LANGUAGE = "languageWord";
    public static final String SECOND_LANGUAGE = "translatedWord";

    private List<Flashcard> flashcards;
    private FlashcardIterator iterator;
    private float pointsSum;

    public FlashcardCollection(Database database, String collectionName, int flashcardAmount)
    {

        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> iterable;
        flashcards = new ArrayList<>();
        List<String> options = database.getOptionWords("languageWord");
        iterable = collection.find(new Document()).limit(flashcardAmount);
        iterable.forEach(document -> flashcards.add(new Flashcard(document.get("languageWord").toString(),
                document.get("translatedWord").toString(), options)));
    }

    public List<Flashcard> getFlashcards()
    {
        return flashcards;
    }

    public int getFlashcardAmount()
    {
        return flashcards.size();
    }

    public FlashcardIterator getIterator() { return iterator; }

    public FlashcardIterator createIterator(FlashcardIterator newIterator)
    {
        this.iterator = newIterator;
        return iterator;
    }

    public float finalizeSummary()
    {
        flashcards.forEach(flashcard -> {
            pointsSum += flashcard.getPoints();
        });
        return pointsSum;
    }
}
