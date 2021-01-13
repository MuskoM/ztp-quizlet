package Flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Level3Flashcard extends FlashcardBaseDecorator{

    private  String[] answers = new String[4];
    private ArrayList<Integer> randomized_option = new ArrayList<>();

    public Level3Flashcard(Flashcard wrapee) {
        super(wrapee);
        type = FlashcardFactory.createFlashcardType("type2",2, Color.ORANGE,10,10);
        for (int i = 0; i < this.options.size(); i++){
            randomized_option.add(i);
        }
        Collections.shuffle(randomized_option);
    }

    @Override
    protected void randomizeAnswers() {
        Random rand = new Random();
        int randomized_number_for_random_answers = rand.nextInt();
        System.out.println("Level 3");
        super.viewFlashcard("");
        if(randomized_number_for_random_answers%4 == 0){
            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = languageWord;
            System.out.println("B. " + answers[1]);

            answers[2] = options.get(randomized_option.get(1));
            System.out.println("C. " + answers[2]);

            answers[3] = options.get(randomized_option.get(2));
            System.out.println("D. " + answers[3]);

        }else if(randomized_number_for_random_answers%4 == 1){
            answers[0] = languageWord;
            System.out.println("A. " + answers[0]);

            answers[1] = options.get(randomized_option.get(0));
            System.out.println("B. " + answers[1]);

            answers[2] = options.get(randomized_option.get(1));
            System.out.println("C. " + answers[2]);

            answers[3] = options.get(randomized_option.get(2));
            System.out.println("D. " + answers[3]);

        }else if(randomized_number_for_random_answers%4 == 2){

            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = options.get(randomized_option.get(1));
            System.out.println("B. " + answers[1]);

            answers[2] = languageWord;
            System.out.println("C. " + answers[2]);

            answers[3] = options.get(randomized_option.get(2));
            System.out.println("D. " + answers[3]);

        }else{

            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = options.get(randomized_option.get(1));
            System.out.println("B. " + answers[1]);

            answers[2] = options.get(randomized_option.get(2));
            System.out.println("C. " + answers[2]);

            answers[3] = languageWord ;
            System.out.println("D. " + answers[3]);

        }
    }


    @Override
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

        JButton answerA = new JButton("A. " + answers[0]);
        JButton answerB = new JButton("B. " + answers[1]);
        JButton answerC = new JButton("C. " + answers[2]);
        JButton answerD = new JButton("D. " + answers[3]);
        answerA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAnswer.setText("A");
                setAnswer("A");
            }
        });

        answerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAnswer.setText("B");
                setAnswer("B");
            }
        });

        answerC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAnswer.setText("C");
                setAnswer("C");
            }
        });

        answerD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAnswer.setText("D");
                setAnswer("D");
            }
        });

        flashcardPanel.add(levelLabel);
        flashcardPanel.add(flashcardLanguageLabel);
        flashcardPanel.add(flashcardTranslatedLabel);
        flashcardPanel.add(answerA);
        flashcardPanel.add(answerB);
        flashcardPanel.add(answerC);
        flashcardPanel.add(answerD);
        flashcardPanel.add(userAnswer);

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
            case "D":
                converted_answer = answers[3];
                break;
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
    }

}
