import Flashcards.Flashcard;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

class TabbedPane extends JPanel {

    private FlashcardCollection flashcardCollection;

    public TabbedPane(FlashcardCollection flashcardCollection) {
        super(new GridLayout(1, 1));

        this.flashcardCollection = flashcardCollection;

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon leariningTabIcon = createImageIcon("icons/dices.png");
        ImageIcon testTabIcon = createImageIcon("icons/book.png");
        ImageIcon databaseTabIcon = createImageIcon("icons/database-storage.png");

        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Test", testTabIcon, panel1,
                "Test your skills");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Learning", leariningTabIcon, panel2,
                "Learn your language");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeDatabaseSessionPanel();
        tabbedPane.addTab("Database", databaseTabIcon, panel3,
                "Manage Database");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //Uncomment the following line to use scrolling tabs.
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(flashcardCollection.getFlashcards().get(0).toString());
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    protected JComponent makeDatabaseSessionPanel(){
        JPanel panel = new JPanel(false);
        JList<Flashcard> flashcardList = new JList(flashcardCollection.getFlashcards().toArray());

        JLabel testLabel = new JLabel();
        flashcardList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                testLabel.setText(flashcardCollection.getFlashcards().get(e.getFirstIndex()).toString());
            }
        });
        flashcardList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        flashcardList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        flashcardList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(flashcardList);
        listScroller.setPreferredSize(new Dimension(250,80));

        panel.add(flashcardList);
        panel.add(testLabel);

        return panel;
    }


    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPane.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}

public class Main {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(FlashcardCollection flashcardCollection) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //Create and set up the content pane.
        frame.getContentPane().add(new TabbedPane(flashcardCollection),
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

        FlashcardCollection fc = new FlashcardCollection(database);


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(fc);
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
