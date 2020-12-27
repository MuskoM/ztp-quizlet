package Flashcards;

public class Level4Decorator extends FlashcardBaseDecorator{

    public String input;

    public Level4Decorator(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }
}
