package Flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Level2Flashcard extends FlashcardBaseDecorator{

    private  String[] answers = new String[3];
    private ArrayList<Integer> randomized_option = new ArrayList<>();
    public Level2Flashcard(Flashcard wrapee) {
        super(wrapee);
        type = FlashcardFactory.createFlashcardType("type2",2, Color.CYAN,10,10);
        for (int i = 0; i < this.options.size(); i++){
            randomized_option.add(i);
        }
        Collections.shuffle(randomized_option);
    }

    @Override
    protected void randomizeAnswers() {
        Random rand = new Random();
        int randomized_number_for_random_answers = rand.nextInt();
        System.out.println("Level 2");
        super.viewFlashcard("canvas");
        if(randomized_number_for_random_answers%3 == 0){
            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = languageWord;
            System.out.println("B. " + answers[1]);

            answers[2] = options.get(randomized_option.get(1));
            System.out.println("C. " + answers[2]);

        }else if(randomized_number_for_random_answers%3 == 1){
            answers[0] = languageWord;
            System.out.println("A. " + answers[0]);

            answers[1] = options.get(randomized_option.get(0));
            System.out.println("B. " + answers[1]);

            answers[2] = options.get(randomized_option.get(1));
            System.out.println("C. " + answers[2]);
        }else{
            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = options.get(randomized_option.get(1));
            System.out.println("B. " + answers[1]);

            answers[2] = languageWord;
            System.out.println("C. " + answers[2]);
        }
    }

    public JPanel getFlashcardPanel() {

        randomizeAnswers();

        // Flashcard
        JPanel flashcardPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        flashcardPanel.setBackground(type.getColor());
        JLabel flashcardTranslatedLabel = new JLabel(this.translatedWord);
        JLabel levelLabel = new JLabel("Level 2");
        JLabel flashcardLanguageLabel = new JLabel(this.languageWord);
        JLabel userAnswer = new JLabel("");
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.LINE_AXIS));
        flashcardPanel.setLayout(new BoxLayout(flashcardPanel,BoxLayout.PAGE_AXIS));

        JButton answerA = new JButton("A. " + answers[0]);
        JButton answerB = new JButton("B. " + answers[1]);
        JButton answerC = new JButton("C. " + answers[2]);
        answerA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer("A");
                userAnswer.setText(answer);
                setAnswered(true);
            }
        });

        answerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer("B");
                userAnswer.setText(answer);
                setAnswered(true);
            }
        });

        answerC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer("C");
                userAnswer.setText(answer);
                setAnswered(true);
            }
        });

        flashcardPanel.add(levelLabel);
        flashcardPanel.add(flashcardLanguageLabel);
        flashcardPanel.add(flashcardTranslatedLabel);
        buttonsPanel.add(answerA);
        buttonsPanel.add(answerB);
        buttonsPanel.add(answerC);
        flashcardPanel.add(buttonsPanel);
        flashcardPanel.add(userAnswer);

        System.out.println("Level 2");

        return flashcardPanel;
    }



    @Override
    public void setAnswer(String answer) {
        String converted_answer = " ";
        switch (answer){
            case "A":
                converted_answer = answers[0];
                break;
            case "B":
                converted_answer = answers[1];
                break;
            case "C":
                converted_answer = answers[2];
                break;
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
        float pts = summarizeAnswer();
        isAnswerCorrect = pts == 1.0f;
        points = pts;
    }
}
