package Flashcards;

public class Level4Flashcard extends FlashcardBaseDecorator{

    public String input;

    public Level4Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
        System.out.println("Level 4");
    }
}
