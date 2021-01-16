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
import java.awt.event.WindowEvent;
import java.util.List;

public class GUI {

    static class TabbedPane extends JPanel {
        public static final String LEARNING_SESSION_LABEL = "Learning Session";
        public static final String TEST_SESSION_LABEL = "Test Session";

        private Database database;
        private FlashcardCollection learningFlashcardCollection;
        private FlashcardCollection testingFlashcardCollection;
        private FlashcardCollection flashcardCollection;
        private FlashcardIterator testIterator;
        private FlashcardIterator learningIterator;

        private JList<String> collNamesList;
        private int testSessionLevel = 1;
        private int learningSessionLevel = 1;
        private int learningFlashcardAmount = 10;
        private int testingFlashcardAmount = 10;
        private String selectedCollectionNameTest;
        private String selectedCollectionNameLearning;

        private Flashcard testingFlashcard;
        private Flashcard learningFlashcard;
        private JPanel flashcardTestPanel;
        private JPanel flashcardLearningPanel;
        private JPanel mainTestSessionPanel;
        private JPanel mainLearningSessionPanel;
        private float points;

        public TabbedPane(Database database) {
            super(new GridLayout(1, 1));
            this.database = database;

            this.flashcardCollection = new FlashcardCollection(database, "pol-eng", 0);

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
            //Main panel
            mainTestSessionPanel = new JPanel();

            mainTestSessionPanel.setLayout(new GridBagLayout());

            //Subpanels - for starting session and for session itself
            JPanel startPanel = makeStartPanel(TEST_SESSION_LABEL);

            //--------------------------------------------------------------------------------
            //Starting session panel

            GridBagConstraints panelConstraint = new GridBagConstraints();
            panelConstraint.weightx = 0.5;
            panelConstraint.weighty = 0.5;
            panelConstraint.anchor = GridBagConstraints.CENTER;
            panelConstraint.gridx = 1;
            panelConstraint.gridy = 1;

            mainTestSessionPanel.add(startPanel, panelConstraint);

            //Start session button
            JButton startButton = new JButton();
            startButton.setText("Start test session");
            startButton.setActionCommand("start-session");

            class StartButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("start-session".equals(e.getActionCommand())) {
                        mainTestSessionPanel.removeAll();
                        mainTestSessionPanel.revalidate();
                        mainTestSessionPanel.repaint();
                        mainTestSessionPanel.add(makeSessionPanel(TEST_SESSION_LABEL));
                    }
                }
            }
            startButton.addActionListener(new StartButtonListener());
            GridBagConstraints buttonConstraint = new GridBagConstraints();
            buttonConstraint.weightx = 0.5;
            buttonConstraint.weighty = 0.5;
//            buttonConstraint.fill = GridBagConstraints.HORIZONTAL;
            buttonConstraint.anchor = GridBagConstraints.CENTER;
            buttonConstraint.gridy = 3;
            buttonConstraint.gridx = 0;
            buttonConstraint.gridwidth = 2;
            mainTestSessionPanel.add(startButton, buttonConstraint);

            return mainTestSessionPanel;
        }

        protected JPanel makeStartPanel(String sessionType) {
            JPanel startPanel = new JPanel();
            startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
            JButton startButton = new JButton(); //it's just here for the dicList and event listener

            //Label
            JLabel label = new JLabel();
            label.setText("Configure your " + sessionType);
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
                        if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                            selectedCollectionNameLearning = source.getSelectedValue().toString();
                        } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                            selectedCollectionNameTest = source.getSelectedValue().toString();
                        }
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
                        if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                            learningSessionLevel = (int) source.getValue();
                        } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                            testSessionLevel = (int) source.getValue();
                        }
//                        sessionPanel.repaint();
//                        sessionPanel.revalidate();
                    }
                }
            }
            levelSlider.addChangeListener(new LevelSliderListener());

            startPanel.add(levelSlider);

            //Flashcard amount slider
            JLabel amountSliderLabel = new JLabel("Choose how many words you want to test:");
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
                        if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                            learningFlashcardAmount = (int) source.getValue();
                        } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                            testingFlashcardAmount = (int) source.getValue();
                        }
                    }
                }
            }
            amountSlider.addChangeListener(new FlashcardAmountSliderListener());

            startPanel.add(amountSlider);

            return startPanel;
        }

        protected JPanel makeSessionPanel(String sessionType) {
            //--------------------------------------------------------------------------------
            //Session panel
            JPanel sessionPanel = new JPanel();

            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                learningFlashcardCollection = new FlashcardCollection(database, selectedCollectionNameLearning, learningFlashcardAmount);
                learningIterator = new LearningIterator(learningFlashcardCollection);
                learningFlashcardCollection.createIterator(learningIterator);
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                testingFlashcardCollection = new FlashcardCollection(database, selectedCollectionNameTest, testingFlashcardAmount);
                testIterator = new TestIterator(testingFlashcardCollection);
                testingFlashcardCollection.createIterator(testIterator);
            }

            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                learningFlashcard = getNewFlashcard(learningSessionLevel, true, sessionType);
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                testingFlashcard = getNewFlashcard(testSessionLevel, true, sessionType);;
            }

            sessionPanel.setLayout(new GridBagLayout());

            GridBagConstraints flashcardConstraint = new GridBagConstraints();
            GridBagConstraints progressBarConstraint = new GridBagConstraints();
            flashcardConstraint.weighty = 0.5;
            flashcardConstraint.weightx = 0.5;
            progressBarConstraint.weightx = 0.5;
            progressBarConstraint.weighty = 0.5;
            //Info about the flashcard Panel
            //Answers Panel
            flashcardConstraint.anchor = GridBagConstraints.CENTER;
            flashcardConstraint.gridy = 2;
            flashcardConstraint.gridx = 0;

            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                flashcardLearningPanel = learningFlashcard.getFlashcardPanel();
                sessionPanel.add(flashcardLearningPanel, flashcardConstraint);
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                flashcardTestPanel = testingFlashcard.getFlashcardPanel();
                sessionPanel.add(flashcardTestPanel, flashcardConstraint);
            }

            JButton applyButton = new JButton("Apply");
            applyButton.setActionCommand("apply");
            GridBagConstraints buttonConstraint = new GridBagConstraints();
            buttonConstraint.weighty = 0.5;
            buttonConstraint.weightx = 0.5;
            buttonConstraint.gridy = 3;
            buttonConstraint.gridx = 0;
            buttonConstraint.anchor = GridBagConstraints.CENTER;

            class ApplyButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("apply".equals(e.getActionCommand())) {
//                        points += flashcard.getPoints();
//                        System.out.println(points);

                        sessionPanel.removeAll();
                        if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                            if (learningSessionLevel == 4)
                                learningFlashcard.proceed();
                            learningFlashcard = getNewFlashcard(learningSessionLevel, learningFlashcard.isAnswerCorrect(), sessionType);
                            flashcardLearningPanel = learningFlashcard.getFlashcardPanel();
                            sessionPanel.add(flashcardLearningPanel, flashcardConstraint);
                        } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                            if (testSessionLevel == 4)
                                testingFlashcard.proceed();
                            testingFlashcard = getNewFlashcard(testSessionLevel, testingFlashcard.isAnswerCorrect(), sessionType);
                            flashcardTestPanel = testingFlashcard.getFlashcardPanel();
                            sessionPanel.add(flashcardTestPanel, flashcardConstraint);
                        }
                        sessionPanel.add(applyButton, buttonConstraint);
                        sessionPanel.revalidate();
                        sessionPanel.repaint();
                    }
                }
            }
            applyButton.addActionListener(new ApplyButtonListener());
            sessionPanel.add(applyButton, buttonConstraint);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setMinimum(0);
            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                progressBar.setMaximum(learningFlashcardCollection.getFlashcardAmount());
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                progressBar.setMaximum(testingFlashcardCollection.getFlashcardAmount());
            }
            progressBar.setValue(0);
            progressBarConstraint.fill = GridBagConstraints.HORIZONTAL;
            progressBarConstraint.anchor = GridBagConstraints.PAGE_END;
            progressBarConstraint.gridy = 2;
            progressBarConstraint.gridx = 0;
            progressBarConstraint.gridwidth = 3;
            sessionPanel.add(progressBar, progressBarConstraint);

            //----------------------------------------------------------------------------------------
            return sessionPanel;
        }

        protected JPanel makeLearningSessionPanel(String text) {
            //Main panel
            mainLearningSessionPanel = new JPanel();

            mainLearningSessionPanel.setLayout(new GridBagLayout());

            //Subpanels - for starting session and for session itself
            JPanel startPanel = makeStartPanel(LEARNING_SESSION_LABEL);

            //--------------------------------------------------------------------------------
            //Starting session panel

            GridBagConstraints panelConstraint = new GridBagConstraints();
            panelConstraint.weightx = 0.5;
            panelConstraint.weighty = 0.5;
            panelConstraint.anchor = GridBagConstraints.CENTER;
            panelConstraint.gridx = 1;
            panelConstraint.gridy = 1;

            mainLearningSessionPanel.add(startPanel, panelConstraint);

            //Start session button
            JButton startButton = new JButton();
            startButton.setText("Start test session");
            startButton.setActionCommand("start-session");

            class StartButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("start-session".equals(e.getActionCommand())) {
                        mainLearningSessionPanel.removeAll();
                        mainLearningSessionPanel.revalidate();
                        mainLearningSessionPanel.repaint();
                        mainLearningSessionPanel.add(makeSessionPanel(LEARNING_SESSION_LABEL));
                    }
                }
            }
            startButton.addActionListener(new StartButtonListener());
            GridBagConstraints buttonConstraint = new GridBagConstraints();
            buttonConstraint.weightx = 0.5;
            buttonConstraint.weighty = 0.5;
//            buttonConstraint.fill = GridBagConstraints.HORIZONTAL;
            buttonConstraint.anchor = GridBagConstraints.CENTER;
            buttonConstraint.gridy = 3;
            buttonConstraint.gridx = 0;
            buttonConstraint.gridwidth = 2;
            mainLearningSessionPanel.add(startButton, buttonConstraint);

            return mainLearningSessionPanel;
        }

        protected int makeSummaryPanel(String sessionType)
        {
            Object[] options = {"Yes", "No"};

            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                return JOptionPane.showOptionDialog(mainLearningSessionPanel, "You got " +
                                learningFlashcardCollection.finalizeSummary() + "/" +
                                learningFlashcardCollection.getFlashcardAmount() + " points. Do you want to start again?",
                        "title xD", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                return JOptionPane.showOptionDialog(mainTestSessionPanel, "You got " +
                                testingFlashcardCollection.finalizeSummary() + "/" +
                                testingFlashcardCollection.getFlashcardAmount() + " points. Do you want to start again?",
                        "title xD", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
            }

            return 0;
        }

        protected Flashcard getNewFlashcard(int lvl, boolean isAnswerCorrect, String sessionType) {
            if (sessionType.equals(LEARNING_SESSION_LABEL)) {
                if (learningFlashcardCollection.getIterator().hasNext(isAnswerCorrect))
                {
                    switch (lvl) {
                        case 1:
                            return new Level1Flashcard(learningFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 2:
                            return new Level2Flashcard(learningFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 3:
                            return new Level3Flashcard(learningFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 4:
                            return new Level4Flashcard(learningFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        default:
                            break;
                    }
                }
                else
                {
                    int x = makeSummaryPanel(sessionType);
                    if ( x == JOptionPane.YES_OPTION )
                    {
                        mainLearningSessionPanel.removeAll();
                        mainLearningSessionPanel.revalidate();
                        mainLearningSessionPanel.repaint();
                        mainLearningSessionPanel.add(makeLearningSessionPanel(sessionType));
                    }
                }
            } else if (sessionType.equals(TEST_SESSION_LABEL)) {
                if (testingFlashcardCollection.getIterator().hasNext(isAnswerCorrect))
                {
                    switch (lvl) {
                        case 1:
                            return new Level1Flashcard(testingFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 2:
                            return new Level2Flashcard(testingFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 3:
                            return new Level3Flashcard(testingFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        case 4:
                            return new Level4Flashcard(testingFlashcardCollection.getIterator().getNext(isAnswerCorrect));
                        default:
                            break;
                    }
                }
                else
                {
                    int x = makeSummaryPanel(sessionType);
                    if ( x == JOptionPane.YES_OPTION )
                    {
                        mainTestSessionPanel.removeAll();
                        mainTestSessionPanel.revalidate();
                        mainTestSessionPanel.repaint();
                        mainTestSessionPanel.add(makeTestSessionPanel(sessionType));
                    }
                    else
                    {
                        Container frame = this.getParent();
                        do
                            frame = frame.getParent();
                        while (!(frame instanceof JFrame));
                        ((JFrame) frame).dispose();
                    }
                }
            }
            return null;
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
                flashcardCollection = new FlashcardCollection(database, collNamesList.getSelectedValue(), 0);
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
