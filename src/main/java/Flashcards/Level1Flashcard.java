package Flashcards;

public class Level1Flashcard extends FlashcardBaseDecorator{

    public String option1 = "PLACEHOLDER1";
    public String option2 = "PLACEHOLDER2";

    public Level1Flashcard(Flashcard wrapee) {
        super(wrapee);
    }


    @Override
    public void viewFlashcard(String canvas) {
        System.out.println("Level 1");
        super.viewFlashcard(canvas);
        System.out.println("A. " + option1);
        System.out.println("B. " + option2);
    }

    @Override
    public void setAnswer(String answer) {
        String converted_answer = " ";
        switch (answer){
            case "A":
                converted_answer = option1;
                break;
            case "B":
                converted_answer = option2;
                break;
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
    }
}
