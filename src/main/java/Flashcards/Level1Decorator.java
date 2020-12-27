package Flashcards;

public class Level1Decorator extends FlashcardBaseDecorator{

    public String option1;
    public String option2;

    public Level1Decorator(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }
}
