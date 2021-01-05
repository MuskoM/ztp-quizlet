package Flashcards;

public class FlashcardBaseDecorator extends Flashcard {

    protected Flashcard wrapee;

    public FlashcardBaseDecorator(Flashcard wrapee){
        this.wrapee = wrapee;
        this.languageWord = wrapee.languageWord;
        this.translatedWord = wrapee.translatedWord;
        this.options = wrapee.options;
    }


    @Override
    public void viewFlashcard(String canvas) {
        super.viewFlashcard(canvas);
    }

}
