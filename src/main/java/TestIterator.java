import Flashcards.Flashcard;

public class TestIterator implements FlashcardIterator{
    FlashcardCollection collection;
    private Flashcard iterationState;
    private int indx = -1;

    public TestIterator(FlashcardCollection collection)
    {
        this.collection = collection;
    }

    //sprawdza czy istnieje kolejny element w kolekcji
    @Override
    public boolean hasNext(boolean isAnswerCorrect) {
        return collection.getFlashcardAmount() > indx + 1;
    }

    //zwraca kolejny element w kolekcji
    @Override
    public Flashcard getNext(boolean isAnswerCorrect) {
        if (collection.getFlashcardAmount() > indx + 1) indx++;

        iterationState = collection.getFlashcards().get(indx);
        return iterationState;
    }

    @Override
    public Flashcard getActual() {
        return iterationState;
    }
}
