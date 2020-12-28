package Flashcards;

public class Level1Flashcard extends FlashcardBaseDecorator{

    public String option1;
    public String option2;

    public Level1Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
        System.out.println("Level 1");
    }
}
