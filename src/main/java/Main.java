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
        FlashcardIterator testIterator = new TestIterator(fc);
        fc.createIterator(testIterator);


        Scanner user_input = new Scanner(System.in);
        int user_choice;
        String user_answer;

        while (true) {
            System.out.println("1.Get flashcards");
            System.out.println("Hello input your function: ");
            user_choice = user_input.nextInt();
            int i = 0;

            switch (user_choice) {
                case 1:
                    //TODO: Pytania są losowane, ale czasami dublują się opcje do wybrania,

                    Flashcard lvl_1 = new Level1Flashcard(fc.getIterator().getNext(false));
                    lvl_1.viewFlashcard("");
                    System.out.println("Select the answer:");
                    user_answer = user_input.next();
                    lvl_1.setAnswer(user_answer);
                    System.out.println(lvl_1.getAnswer() + " " + lvl_1.summarizeAnswer());
                    float points = lvl_1.summarizeAnswer();

                    while (fc.getIterator().hasNext(true)){
                        {
                            Flashcard lvl_3 = new Level3Flashcard(fc.getIterator().getNext(points!=0.0f));
                            lvl_3.viewFlashcard("");
                            System.out.println("Select the answer");
                            user_answer = user_input.next();
                            lvl_3.setAnswer(user_answer);
                            System.out.println(lvl_3.getAnswer() + " " + lvl_3.summarizeAnswer());
                            points = lvl_3.summarizeAnswer();
                        }
                    }

            }

        }

    }
}
