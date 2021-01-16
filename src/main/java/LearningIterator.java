import Flashcards.Flashcard;

public class LearningIterator implements FlashcardIterator {
    private FlashcardCollection collection;
    private Flashcard iterationState;
    private int indx = -1;

    public LearningIterator(FlashcardCollection collection) {
        this.collection = collection;
        this.iterationState = collection.getFlashcards().get(indx+1);
    }

    @Override
    public boolean hasNext(boolean isAnswerCorrect) {
        if (isAnswerCorrect) return collection.getFlashcardAmount() > indx + 1;
        else return true;
        //przy odpowiedzi nieprawdziwej iterator zwraca ten sam element kolekcji, więc odpowiedź zawsze będzie true
    }

    @Override
    public Flashcard getNext(boolean isAnswerCorrect) {
        if (!hasNext(isAnswerCorrect)) return null;
        if (isAnswerCorrect)
            if (collection.getFlashcardAmount() > indx + 1) indx++;

        iterationState = collection.getFlashcards().get(indx);
        return iterationState;
    }

    @Override
    public Flashcard getActual() {
        return iterationState;
    }
}
