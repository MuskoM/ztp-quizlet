import Flashcards.Flashcard;
import Flashcards.Level2Flashcard;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;



public class Main {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(Database database) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //Create and set up the content pane.
        frame.getContentPane().add(new GUI.TabbedPane(database),
                BorderLayout.CENTER);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        TestSession testSession = new TestSession();
        LearningSession learningSession = new LearningSession();
        EditSession editSession = new EditSession();

        Database database = Database.getInstance();

        FlashcardCollection fc = new FlashcardCollection(database,"pol-eng");


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(database);
            }
        });


//
//        //TODO: Adasiek, jak to ma działać?
//        System.out.println(fc.getFlashcardAmount());
//        FlashcardIterator testIterator = new TestIterator(fc);
//        fc.createIterator(testIterator);
//
//
//        Scanner user_input = new Scanner(System.in);
//        int user_choice;
//        String user_answer;
//
//        while (true) {
//            System.out.println("1.Get flashcards");
//            System.out.println("Hello input your function: ");
//            user_choice = user_input.nextInt();
//            int i = 0;
//
//            switch (user_choice) {
//                case 1:
//                    //TODO: Pytania są losowane, ale czasami dublują się opcje do wybrania,
//
//                    Flashcard lvl_1 = new Level1Flashcard(fc.getIterator().getNext(false));
//                    lvl_1.viewFlashcard("");
//                    System.out.println("Select the answer:");
//                    user_answer = user_input.next();
//                    lvl_1.setAnswer(user_answer);
//                    System.out.println(lvl_1.getAnswer() + " " + lvl_1.summarizeAnswer());
//                    float points = lvl_1.summarizeAnswer();
//
//                    while (fc.getIterator().hasNext(true)){
//                        {
//                            Flashcard lvl_3 = new Level3Flashcard(fc.getIterator().getNext(points!=0.0f));
//                            lvl_3.viewFlashcard("");
//                            System.out.println("Select the answer");
//                            user_answer = user_input.next();
//                            lvl_3.setAnswer(user_answer);
//                            System.out.println(lvl_3.getAnswer() + " " + lvl_3.summarizeAnswer());
//                            points = lvl_3.summarizeAnswer();
//                        }
//                    }
//
//            }
//
//        }
//
  }
}
