import Flashcards.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        private FlashcardCollection flashcardCollection;
        private JList<String> collNamesList;
        private int level = 1;
        private int flashcardAmount = 10;

        public TabbedPane(Database database) {
            super(new GridLayout(1, 1));
            //TODO: Zrobić ekran startowy dla testów i learningu
            this.database = database;
            flashcardCollection = new FlashcardCollection(database, "pol-eng");

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
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());

            //Subpanels - for starting session and for session itself
            JPanel startPanel = new JPanel();
            JPanel sessionPanel = new JPanel();

            //--------------------------------------------------------------------------------
            //Starting session panel
            startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
            JButton startButton = new JButton(); //it's just here for the dicList and event listener

            //Label
            JLabel label = new JLabel();
            label.setText("Configure your test session");
            label.setHorizontalAlignment(JLabel.CENTER);
            startPanel.add(label);

            //Dictionary
            JLabel dictionaryListLabel = new JLabel("Choose dictionary:", JLabel.CENTER);
            startPanel.add(dictionaryListLabel);

            JList dictionaryList = new JList(database.getCollectionsNames().toArray(new String[0]));
            dictionaryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            dictionaryList.setLayoutOrientation(JList.VERTICAL);
            dictionaryList.setVisibleRowCount(-1);

            class DictionaryListListener implements ListSelectionListener {
                public void valueChanged(ListSelectionEvent e) {
                    JList source = (JList) e.getSource();
                    if (!e.getValueIsAdjusting()) {
                        startButton.setEnabled(source.getSelectedIndex() != -1);
                    }
                }
            }
            dictionaryList.addListSelectionListener(new DictionaryListListener());

            JScrollPane dictionaryListScroller = new JScrollPane(dictionaryList);
            dictionaryListScroller.setPreferredSize(new Dimension(100, 150));

            startPanel.add(dictionaryListScroller);

            //Level slider
            JLabel levelSliderLabel = new JLabel("Choose your level:", JLabel.CENTER);
            startPanel.add(levelSliderLabel);

            JSlider levelSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
            levelSlider.setMajorTickSpacing(1);
            levelSlider.setMinorTickSpacing(1);
            levelSlider.setPaintTicks(true);
            levelSlider.setPaintLabels(true);
            levelSlider.setToolTipText("Level");

            class LevelSliderListener implements ChangeListener {
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider) e.getSource();

                    if (!source.getValueIsAdjusting()) {
                        level = (int) source.getValue();
                    }
                }
            }
            levelSlider.addChangeListener(new LevelSliderListener());

            startPanel.add(levelSlider);

            //Flashcard amount slider
            JLabel amountSliderLabel = new JLabel("Choose how much words you want to test:");
            startPanel.add(amountSliderLabel);

            JSlider amountSlider = new JSlider(JSlider.HORIZONTAL, 5, 50, 10);
            amountSlider.setMajorTickSpacing(5);
            amountSlider.setMinorTickSpacing(1);
            amountSlider.setPaintTicks(true);
            amountSlider.setPaintLabels(true);
            amountSlider.setToolTipText("Flashcard amount");

            class FlashcardAmountSliderListener implements ChangeListener {
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider) e.getSource();

                    if (!source.getValueIsAdjusting()) {
                        flashcardAmount = (int) source.getValue();
                    }
                }
            }
            amountSlider.addChangeListener(new FlashcardAmountSliderListener());

            startPanel.add(amountSlider);

            //Start session button
            startButton.setText("Start test session");
            startButton.setActionCommand("start-session");

            class StartButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("start-session".equals(e.getActionCommand())) {
                        mainPanel.removeAll();
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        mainPanel.add(sessionPanel);
                    }
                }
            }
            startButton.addActionListener(new StartButtonListener());

            startPanel.add(startButton);

            //--------------------------------------------------------------------------------
            //Session panel
            Flashcard flashcard = new Level3Flashcard(flashcardCollection.getIterator().getNext(true));

            sessionPanel.setLayout(new GridBagLayout());

            GridBagConstraints flashcardConstraint = new GridBagConstraints();
            GridBagConstraints progressBarConstraint = new GridBagConstraints();
            flashcardConstraint.weighty = 0.5;
            flashcardConstraint.weightx = 0.5;
            progressBarConstraint.weightx = 0.5;
            progressBarConstraint.weighty = 0.5;
            //Info about the flashcard Panel
            //Answers Panel
            JPanel flashcardPanel = flashcard.getFlashcardPanel();
            flashcardConstraint.anchor = GridBagConstraints.CENTER;
            flashcardConstraint.gridy = 2;
            flashcardConstraint.gridx = 0;
            sessionPanel.add(flashcardPanel, flashcardConstraint);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setMinimum(0);
            progressBar.setMaximum(flashcardCollection.getFlashcardAmount());
            progressBar.setValue(0);
            progressBarConstraint.fill = GridBagConstraints.HORIZONTAL;
            progressBarConstraint.anchor = GridBagConstraints.PAGE_END;
            progressBarConstraint.gridy = 2;
            progressBarConstraint.gridx = 0;
            progressBarConstraint.gridwidth = 3;
            sessionPanel.add(progressBar, progressBarConstraint);

            //----------------------------------------------------------------------------------------
            mainPanel.add(startPanel);
            return mainPanel;

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

        protected JPanel makeDataPanel() {
            return new JPanel();
        }


        protected JPanel makeDatabaseMgmntPanel() {
            JPanel panel = new JPanel();
            JSplitPane databaseViewer = new JSplitPane();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            JPanel languagesPanel = new JPanel();
            List<String> collNames = database.getCollectionsNames();
            DefaultListModel<String> listAdapter = new DefaultListModel<>();
            listAdapter.addAll(collNames);
            collNamesList = new JList<>(listAdapter);
            collNamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(collNamesList);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Languages"));
            languagesPanel.add(scrollPane);

            JButton addCollection = new JButton("Add new language");

            addCollection.addActionListener(e -> {
                String value = JOptionPane.showInputDialog(panel,
                        "Give language pair ex. eng-pol",
                        "Add Collection",
                        JOptionPane.INFORMATION_MESSAGE);
                try {
                    database.addCollection(value);
                    listAdapter.addElement(value);

                } catch (Exception ex) {
                    //FIXME add exception handling!
                    /* ... */
                }
            });

            JButton deleteCollection = new JButton("Delete language");

            deleteCollection.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(panel,
                        "Do you want to delete " + collNamesList.getSelectedValue() + "?"
                );

                if (confirm == JOptionPane.YES_NO_OPTION) {
                    database.removeCollection(collNamesList.getSelectedValue());
                    listAdapter.removeElementAt(collNamesList.getSelectedIndex());
                }
            });
            languagesPanel.setLayout(new BoxLayout(languagesPanel, BoxLayout.PAGE_AXIS));
            languagesPanel.add(addCollection);
            languagesPanel.add(deleteCollection);
            databaseViewer.setLeftComponent(languagesPanel);

            JPanel flashcardsTablePanel = new JPanel();
            TableModelAdapter tableModelAdapter = new TableModelAdapter(flashcardCollection.getFlashcards(), database, "pol-eng");
            JTable flashcardTable = new JTable(tableModelAdapter);
            scrollPane = new JScrollPane(flashcardTable);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Flashcards"));

            flashcardsTablePanel.add(scrollPane);
            flashcardsTablePanel.setLayout(new BoxLayout(flashcardsTablePanel, BoxLayout.PAGE_AXIS));

            JButton addFlashcardBtn = new JButton("Add pair");

            addFlashcardBtn.addActionListener(e -> {
                String userInput = JOptionPane.showInputDialog("Input your pair here",
                        "foreignLanguage:yourLanguage");
                String[] arr = userInput.split(":");
                System.out.println(arr[0] + " " + arr[1]);
                database.addFlashcard(arr[0], arr[1], collNamesList.getSelectedValue());
                tableModelAdapter.setValueAt(arr[0], flashcardTable.getRowCount(), 0);
                tableModelAdapter.setValueAt(arr[1], flashcardTable.getRowCount(), 1);
            });


            JButton removeFlashcardBtn = new JButton("Remove pair");

            removeFlashcardBtn.addActionListener(e -> {
            });

            flashcardsTablePanel.add(addFlashcardBtn);
            flashcardsTablePanel.add(removeFlashcardBtn);

            databaseViewer.setRightComponent(flashcardsTablePanel);


            collNamesList.addListSelectionListener(e -> {
                flashcardCollection = new FlashcardCollection(database, collNamesList.getSelectedValue());
                tableModelAdapter.setFlashcards(flashcardCollection.getFlashcards(), collNamesList.getSelectedValue());
            });


            panel.add(databaseViewer);

            return panel;
        }

        protected JComponent makeDatabaseSessionPanel() {
            JPanel panel = new JPanel(false);
            JPanel databaseMgmtPanel = makeDatabaseMgmntPanel();
            JPanel dataPanel = makeDataPanel();
            panel.add(databaseMgmtPanel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
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

    public class FlashcardInputDialog extends JDialog {


    }


}
