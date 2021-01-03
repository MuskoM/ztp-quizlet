import Flashcards.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        Scanner user_input = new Scanner(System.in);
        int user_choice;
        String user_answer;

        while(true) {
            System.out.println("1.Get flashcards");
            System.out.println("Hello input your function: ");
            user_choice = user_input.nextInt();

            switch (user_choice) {
                case 1:
                    for (int i = 0; i < words.size(); i++) {
                        switch (i % 4) {
                            case 0:
                                Flashcard lvl_1 = new Level1Flashcard(words.get(i));
                                lvl_1.viewFlashcard("");
                                System.out.println("Select the answer");
                                user_answer = user_input.next();
                                lvl_1.setAnswer(user_answer);
                                System.out.println(lvl_1.getAnswer());
                                break;
                            case 1:
                                Flashcard lvl_2 = new Level2Flashcard(words.get(i));
                                lvl_2.viewFlashcard("");
                                System.out.println("Select the answer");
                                user_answer = user_input.next();
                                lvl_2.setAnswer(user_answer);
                                System.out.println(lvl_2.getAnswer());
                                break;
                            case 2:
                                Flashcard lvl_3 = new Level3Flashcard(words.get(i));
                                lvl_3.viewFlashcard("");
                                System.out.println("Select the answer");
                                user_answer = user_input.next();
                                lvl_3.setAnswer(user_answer);
                                System.out.println(lvl_3.getAnswer());
                                break;
                            case 3:
                                Flashcard lvl_4 = new Level4Flashcard(words.get(i));
                                lvl_4.viewFlashcard("");
                                System.out.println("Select the answer");
                                user_answer = user_input.next();
                                lvl_4.setAnswer(user_answer);
                                System.out.println(lvl_4.getAnswer());
                                break;
                        }
                    }
                    break;
                default:
            }

        }

        }
    }
