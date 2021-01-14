import Flashcards.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class GUI {

    static class TabbedPane extends JPanel {

        private Database database;
        private FlashcardCollection  flashcardCollection;
        private JList<Flashcard> flashcardList;

        public TabbedPane(Database database) {
            super(new GridLayout(1, 1));
            //TODO: Zrobić ekran startowy dla testów i learningu
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

            //TODO: Losowanie fiszek dla danej sesji z podanego zakresu i wybór trudności

            FlashcardIterator testIterator = new TestIterator(flashcardCollection);
            flashcardCollection.createIterator(testIterator);

            //Main panel
            JPanel panel = new JPanel();
            Flashcard flashcard = new Level3Flashcard(flashcardCollection.getIterator().getNext(true));

            panel.setLayout(new GridBagLayout());

            GridBagConstraints flashcardconstraint = new GridBagConstraints();
            GridBagConstraints progressBarConstraint = new GridBagConstraints();
            flashcardconstraint.weighty = 0.5;
            flashcardconstraint.weightx = 0.5;
            progressBarConstraint.weightx = 0.5;
            progressBarConstraint.weighty = 0.5;
            //Info about the flashcard Panel
            //Answers Panel
            JPanel flashcardPanel = flashcard.getFlashcardPanel();
            flashcardconstraint.anchor = GridBagConstraints.CENTER;
            flashcardconstraint.gridy = 2;
            flashcardconstraint.gridx = 0;
            panel.add(flashcardPanel, flashcardconstraint);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setMinimum(0);
            progressBar.setMaximum(flashcardCollection.getFlashcardAmount());
            progressBar.setValue(0);
            progressBarConstraint.fill = GridBagConstraints.HORIZONTAL;
            progressBarConstraint.anchor = GridBagConstraints.PAGE_END;
            progressBarConstraint.gridy = 2;
            progressBarConstraint.gridx = 0;
            progressBarConstraint.gridwidth = 3;
            panel.add(progressBar, progressBarConstraint);
            return panel;

        }

        protected JPanel makeLearningSessionPanel(String text) {
            //TODO: Losowanie fiszek dla danej sesji z podanego zakresu i wybór trudności
            FlashcardIterator learningIterator = new LearningIterator(flashcardCollection);
            flashcardCollection.createIterator(learningIterator);

            //Main panel
            JPanel panel = new JPanel();
            Flashcard flashcard = new Level2Flashcard(flashcardCollection.getIterator().getNext(true));

//            panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
            panel.setLayout(new GridBagLayout());
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
                    String value = JOptionPane.showInputDialog(panel,
                            "Give language pair ex. eng-pol",
                            "Add Collection",
                            JOptionPane.INFORMATION_MESSAGE);
                    try{
                        database.addCollection(value);
                    } catch(Exception ex) {
                        //FIXME add exception handling!
                        /* ... */
                    };
                }
            });

            JButton deleteCollection = new JButton("Delete language");

            deleteCollection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            panel.add(addCollection);
            panel.add(deleteCollection);

            return panel;
        }


        protected JPanel makeDatabaseMgmntPanel(){
            JPanel panel = new JPanel();
            List collNames = database.getCollectionsNames();
            JList<String> collNamesList = new JList(collNames.toArray());
            this.flashcardList = new JList(flashcardCollection.getFlashcards().toArray());

            JSplitPane databaseViewer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,collNamesList, flashcardList);
            databaseViewer.setOneTouchExpandable(true);
            databaseViewer.setDividerLocation(150);
            Dimension minSize = new Dimension(200,200);
            collNamesList.setMinimumSize(minSize);
            flashcardList.setMinimumSize(minSize);

            JLabel testLabel = new JLabel();

            collNamesList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    FlashcardCollection fc = new FlashcardCollection(database,collNamesList.getSelectedValue());
                    flashcardList = new JList(fc.getFlashcards().toArray());

                    databaseViewer.setRightComponent(flashcardList);
                }
            });

            JList c = (JList) databaseViewer.getRightComponent();
            c.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println(c.getSelectedValue());
                }
            });

            flashcardList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            flashcardList.setVisibleRowCount(-1);

            collNamesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            collNamesList.setVisibleRowCount(-1);


            panel.add(databaseViewer);

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
