package Flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Level1Flashcard extends FlashcardBaseDecorator{

    private String[] answers = new String[2];
    private ArrayList<Integer> randomized_option = new ArrayList<>();

    public Level1Flashcard(Flashcard wrapee) {
        super(wrapee);
        type = FlashcardFactory.createFlashcardType("1",1, Color.GREEN,10,10);
        for (int i = 0; i < this.options.size(); i++){
            randomized_option.add(i);
        }
        Collections.shuffle(randomized_option);
    }

    /***
     * Logika oraz wyświetlanie odpowiedzi do fiszki
     * @param canvas
     */
    @Override
    public void viewFlashcard(String canvas) {
        Random rand = new Random();
        System.out.println("Level 1");
        super.viewFlashcard(canvas);
        if(rand.nextInt()%2 == 0){
            answers[0] = options.get(randomized_option.get(0));
            System.out.println("A. " + answers[0]);

            answers[1] = languageWord;
            System.out.println("B. " + languageWord);

        }else{
            answers[0] = languageWord;
            System.out.println("A. " + languageWord);

            answers[1] = options.get(randomized_option.get(1));
            System.out.println("B. " + answers[1]);
        }
    }

    @Override
    protected void randomizeAnswers() {
        Random rand = new Random();
        if(rand.nextInt()%2 == 0){
            answers[0] = options.get(randomized_option.get(0));

            answers[1] = languageWord;

        }else{
            answers[0] = languageWord;

            answers[1] = options.get(randomized_option.get(1));
        }
    }

    public JPanel getFlashcardPanel() {

        randomizeAnswers();

        // Flashcard
        JPanel flashcardPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        flashcardPanel.setBackground(type.getColor());
        JLabel flashcardTranslatedLabel = new JLabel(this.translatedWord);
        JLabel levelLabel = new JLabel("Level 1");
        JLabel userAnswer = new JLabel("");
        flashcardPanel.setLayout(new BoxLayout(flashcardPanel,BoxLayout.PAGE_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.LINE_AXIS));
        JButton answerA = new JButton("A. " + answers[0]);
        JButton answerB = new JButton("B. " + answers[1]);
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

        flashcardPanel.add(levelLabel);
        flashcardPanel.add(flashcardTranslatedLabel);
        buttonsPanel.add(answerA);
        buttonsPanel.add(answerB);
        flashcardPanel.add(buttonsPanel);
        flashcardPanel.add(userAnswer);

        System.out.println("Level 1");

        return flashcardPanel;
    }

    @Override
    protected void setAnswer(String answer) {
        String converted_answer = " ";
        switch (answer){
            case "A":
                converted_answer = answers[0];
                break;
            case "B":
                converted_answer = answers[1];
                break;
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
        float pts = summarizeAnswer();
        isAnswerCorrect = pts == 1.0f;
        wrapee.setPoints(pts);
    }
}
