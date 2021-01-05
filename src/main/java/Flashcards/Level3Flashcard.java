package Flashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Level3Flashcard extends FlashcardBaseDecorator{

    private  String[] answers = new String[4];
    private ArrayList<Integer> randomized_option = new ArrayList<>();

    public Level3Flashcard(Flashcard wrapee) {

        super(wrapee);
        for (int i = 0; i < this.options.size(); i++){
            randomized_option.add(i);
        }
        Collections.shuffle(randomized_option);
    }

    @Override
    public void viewFlashcard(String canvas) {
        Random rand = new Random();
        int randomized_number_for_random_answers = rand.nextInt();
        System.out.println("Level 3");
        super.viewFlashcard(canvas);
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
