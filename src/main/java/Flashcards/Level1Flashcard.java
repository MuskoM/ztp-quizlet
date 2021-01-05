package Flashcards;

import java.util.Random;

public class Level1Flashcard extends FlashcardBaseDecorator{

    private String[] answers = new String[2];
    private int randomized_option;

    public Level1Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    /***
     * Logika oraz wyświetlanie odpowiedzi do fiszki
     * @param canvas
     */
    @Override
    public void viewFlashcard(String canvas) {
        Random rand = new Random();
        randomized_option = rand.nextInt(options.length);
        System.out.println("Level 1");
        super.viewFlashcard(canvas);
        if(rand.nextInt()%2 == 0){
            answers[0] = options[randomized_option%options.length];
            System.out.println("A. " + answers[0]);

            answers[1] = languageWord;
            System.out.println("B. " + languageWord);

        }else{
            answers[0] = languageWord;
            System.out.println("A. " + languageWord);

            answers[1] = options[randomized_option%options.length];
            System.out.println("B. " + answers[1]);
        }
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
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
    }

}
