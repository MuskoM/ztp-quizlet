package FlashcardCollection;

import Flashcards.Flashcard;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import Database;

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
        List<Flashcard> words = new ArrayList<>();
        iterable = collection.find(new Document());
        iterable.forEach(document -> words.add(new Flashcard(document.get("languageWord").toString(),
                document.get("translatedWord").toString())));
    }

    public List<Flashcard> getFlashcards()
    {
        return flashcards;
    }

    public int getFlashcardAmount()
    {
        return flashcards.size();
    }

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
