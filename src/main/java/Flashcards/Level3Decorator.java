package Flashcards;

public class Level3Decorator extends FlashcardBaseDecorator{

    public String option1;
    public String option2;
    public String option3;
    public String option4;

    public Level3Decorator(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }
}
