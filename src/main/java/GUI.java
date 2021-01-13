import Flashcards.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI {

    static class TabbedPane extends JPanel {

        private Database database;
        private FlashcardFactory flashcardFactory = new FlashcardFactory();
        private FlashcardCollection  flashcardCollection;

        public TabbedPane(Database database) {
            super(new GridLayout(1, 1));

            this.database = database;
            flashcardCollection = new FlashcardCollection(database,"pol-eng");

            JTabbedPane tabbedPane = new JTabbedPane();
            ImageIcon learningTabIcon = createImageIcon("icons/dices.png");
            ImageIcon testTabIcon = createImageIcon("icons/book.png");
            ImageIcon databaseTabIcon = createImageIcon("icons/database-storage.png");

            JComponent panel1 = makeTestSessionPanel("");
            tabbedPane.addTab("Test", testTabIcon, panel1,
                    "Test your skills");
            tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

            JComponent panel2 = makeLearningSessionPanel("Panel #2");
            tabbedPane.addTab("Learning", learningTabIcon, panel2,
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

        protected JPanel makeTestSessionPanel(String text) {
            FlashcardIterator testIterator = new TestIterator(flashcardCollection);
            flashcardCollection.createIterator(testIterator);

            //Main panel
            JPanel panel = new JPanel();

            //TODO: Add progressbar

            Flashcard flashcard = new Level3Flashcard(flashcardCollection.getIterator().getNext(true));
            //TODO: Pobawić się LayoutMngrmi i wycentrować fiszkę na ekranie
            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));

            //Info about the flashcard Panel
            //Answers Panel
            JPanel flashcardPanel = flashcard.getFlashcardPanel();

            panel.add(flashcardPanel);
            return panel;
        }


        protected JPanel makeLearningSessionPanel(String text) {
            FlashcardIterator learningIterator = new LearningIterator(flashcardCollection);
            flashcardCollection.createIterator(learningIterator);

            //Main panel
            JPanel panel = new JPanel();
            Flashcard flashcard = new Level3Flashcard(flashcardCollection.getIterator().getNext(true));
            //TODO: Pobawić się LayoutMngrmi i wycentrować fiszkę na ekranie
            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));

            //Info about the flashcard Panel
            //Answers Panel
            JPanel flashcardPanel = flashcard.getFlashcardPanel();

            panel.add(flashcardPanel);
            return panel;
        }

        protected JPanel makeDataPanel(){
            JPanel panel = new JPanel();
            JButton addCollection = new JButton("Add new language");
            addCollection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JButton deleteCollection = new JButton("Delete language");

            panel.add(addCollection);
            panel.add(deleteCollection);

            return panel;
        }


        protected JPanel makeDatabaseMgmntPanel(){
            JPanel panel = new JPanel();

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

        protected JComponent makeDatabaseSessionPanel(){
            JPanel panel = new JPanel(false);
            JPanel databaseMgmtPanel = makeDatabaseMgmntPanel();
            JPanel dataPanel = makeDataPanel();
            panel.add(databaseMgmtPanel);
            panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
            panel.add(dataPanel);
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

}
