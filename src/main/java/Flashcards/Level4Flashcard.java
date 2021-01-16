package Flashcards;

import Summarization.DifficultSummary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Level4Flashcard extends FlashcardBaseDecorator{

    private  String[] answers = new String[3];

    public Level4Flashcard(Flashcard wrapee) {
        super(wrapee);
        type = FlashcardFactory.createFlashcardType("3",3, Color.RED,1,1);
        wrapee.summContext.setSummaryStrategy(new DifficultSummary());
    }

    @Override
    public void viewFlashcard(String canvas) {
        System.out.println("Level 4");
        super.viewFlashcard(canvas);
    }

    public JPanel getFlashcardPanel() {

        randomizeAnswers();

        // Flashcard
        JPanel flashcardPanel = new JPanel();
        flashcardPanel.setBackground(type.getColor());
        JLabel flashcardTranslatedLabel = new JLabel(this.translatedWord);
        JLabel levelLabel = new JLabel("Level 2");
        JLabel flashcardLanguageLabel = new JLabel(this.languageWord);
        JLabel userAnswer = new JLabel("");
        flashcardPanel.setLayout(new BoxLayout(flashcardPanel,BoxLayout.PAGE_AXIS));

        JTextField userAnswerField = new JTextField();

        userAnswerField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    userAnswer.setText(e.getDocument().getText(0,e.getDocument().getLength()));
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    userAnswer.setText(e.getDocument().getText(0,e.getDocument().getLength()));
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    userAnswer.setText(e.getDocument().getText(0,e.getDocument().getLength()));
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }
        });

        JButton answerA = new JButton("Submit answer");
        answerA.setActionCommand("apply");

        answerA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer(userAnswer.getText());
            }
        });

        flashcardPanel.add(levelLabel);
        flashcardPanel.add(flashcardLanguageLabel);
        flashcardPanel.add(flashcardTranslatedLabel);
        flashcardPanel.add(userAnswerField);
        flashcardPanel.add(answerA);
        flashcardPanel.add(userAnswer);

        System.out.println("Level 1");


        return flashcardPanel;
    }

    public void proceed()
    {
        setAnswered(true);
        float pts = summarizeAnswer();
        isAnswerCorrect = pts == 1.0f;
        points = pts;
    }
}
