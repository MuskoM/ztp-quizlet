package Flashcards;

public class Level2Decorator extends FlashcardBaseDecorator{

    public String option1;
    public String option2;
    public String option3;

    public Level2Decorator(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }
}
