package Flashcards;

public class Level2Flashcard extends FlashcardBaseDecorator{

    public String option1 = "PLACEHOLDER";
    public String option2 = "PLACEHOLDER";
    public String option3 = "PLACEHOLDER";

    public Level2Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        System.out.println("Level 2");
        super.viewFlashcard(canvas);
        System.out.println("A. " + option1);
        System.out.println("B. " + option2);
        System.out.println("C. " + option3);
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
            case "C":
                converted_answer = option3;
                break;
            default:
                System.out.println("Wrong answer");
        }

        super.setAnswer(converted_answer);
    }
}
