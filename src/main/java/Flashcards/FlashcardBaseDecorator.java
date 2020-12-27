package Flashcards;

public class FlashcardBaseDecorator extends Flashcard {

    private Flashcard wrapee;

    public FlashcardBaseDecorator(Flashcard wrapee){
        this.wrapee = wrapee;
    }

    public void viewFlashcard(String canvas){

    }

}
