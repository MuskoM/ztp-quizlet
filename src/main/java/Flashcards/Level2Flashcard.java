package Flashcards;

public class Level2Flashcard extends FlashcardBaseDecorator{

    public String option1;
    public String option2;
    public String option3;

    public Level2Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
        System.out.println("Level 2");
    }
}
