package Flashcards;

public class FlashcardBaseDecorator extends Flashcard {

    protected Flashcard wrapee;
    protected String[] options;

    public FlashcardBaseDecorator(Flashcard wrapee){
        this.wrapee = wrapee;
        this.languageWord = wrapee.languageWord;
        this.translatedWord = wrapee.translatedWord;
    }


    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
