package FlashcardCollection;

import Flashcards.Flashcard;

public class LearningIterator implements FlashcardIterator {
    private FlashcardCollection collection;
    private boolean iterationState;

    public LearningIterator(FlashcardCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext(boolean isAnswerCorrect) {
        if (isAnswerCorrect) {
            //jeżeli odpowiedź jest prawdziwa to ma sprawdzić czy istnieje kolejny element w kolekcji
            //jak nie ma to zwraca false, jak jest to true
            return false;
        } else {
            //przy odpowiedzi nieprawdziwej iterator zwraca ten sam element kolekcji, więc odpowiedź zawsze będzie true
            return true;
        }
    }

    @Override
    public Flashcard getNext(boolean isAnswerCorrect) {
        return null;
    }
}
