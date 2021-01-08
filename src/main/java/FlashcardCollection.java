import Flashcards.Flashcard;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCollection {
    private List<Flashcard> flashcards;
    private FlashcardIterator iterator;
    private float[] points;
    private float pointsSum;

    public FlashcardCollection(Database database)
    {
        MongoCollection<Document> collection = database.getCollection("pol-eng");
        FindIterable<Document> iterable;
        flashcards = new ArrayList<>();
        List<String> options = database.getOptionWords("languageWord");
        iterable = collection.find(new Document());
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

    public void setPointsForOneWord(int indx, float point)
    {
        points[indx] = point;
    }

    public void finalizeSummary()
    {
        for (float point : points) {
            pointsSum += point;
        }
        System.out.println("You got " + pointsSum + " points. Congratulations!");
    }
}
