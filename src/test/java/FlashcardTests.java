import Flashcards.Flashcard;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static org.junit.Assert.assertEquals;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

@Category(GlobalTests.class)
public class FlashcardTests {

    static MongoCollection<Document> collection;

    @BeforeClass
    public static void initializeCollection(){
        Database database = Database.getInstance();
        collection = database.getCollection("pol-eng");
    }

    @Test
    public void isFlashcardCreatedProperly(){
        FindIterable<Document> iterable = collection.find(new Document());
        List<Document> wordsList = new ArrayList<>();
        List<Flashcard> flashcardList = new ArrayList<>();
        iterable.forEach(document -> {
            wordsList.add(document);
        });

        wordsList.forEach(word -> {
            flashcardList.add(new Flashcard(word.get("languageWord").toString(),
                    word.get("translatedWord").toString()));
        });

        assertEquals(wordsList.get(0).get("languageWord").toString(),flashcardList.get(0).getBaseWord());
    }

}
