import Flashcards.Flashcard;

public interface FlashcardIterator {
    boolean hasNext(boolean isAnswerCorrect);
    Flashcard getNext(boolean isAnswerCorrect);
    Flashcard getActual();
}
