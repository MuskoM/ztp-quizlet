import Flashcards.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) {

        Database database = Database.getInstance();
        FlashcardCollection fc = new FlashcardCollection(database);

        //TODO: Adasiek, jak to ma działać?
        System.out.println(fc.getFlashcardAmount());
        FlashcardIterator testIterator = new TestIterator();
        fc.createIterator(testIterator);


//        MongoCollection<Document> collection = database.getCollection("pol-eng");
//
//        FindIterable<Document> iterable;
//        List<String> options = database.getOptionWords("languageWord");
//
//        List<Flashcard> words = new ArrayList<>();
//        iterable = collection.find(new Document());
//        iterable.forEach(document -> {
//            words.add(new Flashcard(document.get("languageWord").toString(),
//                    document.get("translatedWord").toString(),options));
//        });
//
//        Scanner user_input = new Scanner(System.in);
//        int user_choice;
//        String user_answer;
//
//        while (true) {
//            System.out.println("1.Get flashcards");
//            System.out.println("Hello input your function: ");
//            user_choice = user_input.nextInt();
//
//            switch (user_choice) {
//                case 1:
//                    //TODO: Pytania są losowane, ale czasami dublują się opcje do wybrania,
//                    for (int i = 0; i < words.size(); i++) {
//                        //Logika odpowiadania na pytanie z fiszki
//                        if(i%4 == 0) {
//                            Flashcard lvl_1 = new Level3Flashcard(words.get(i));
//                            lvl_1.viewFlashcard("");
//                            System.out.println("Select the answer");
//                            user_answer = user_input.next();
//                            lvl_1.setAnswer(user_answer);
//                            System.out.println(lvl_1.getAnswer() + " " + lvl_1.summarizeAnswer());
//                        }
//                        else if(i%4==1){
//                            Flashcard lvl_1 = new Level1Flashcard(words.get(i));
//                            lvl_1.viewFlashcard("");
//                            System.out.println("Select the answer");
//                            user_answer = user_input.next();
//                            lvl_1.setAnswer(user_answer);
//                            System.out.println(lvl_1.getAnswer() + " " + lvl_1.summarizeAnswer());
//                        }
//
//                    }
//            }
//
//        }

    }
}
