package FlashcardCollection;

import Flashcards.Flashcard;

public class TestIterator implements FlashcardIterator{
    @Override
    public boolean hasNext(boolean isAnswerCorrect) {
        //sprawdza czy istnieje kolejny element w kolekcji
        return false;
    }

    @Override
    public Flashcard getNext(boolean isAnswerCorrect) {
        //zwraca kolejny element w kolekcji
        return null;
    }
}
