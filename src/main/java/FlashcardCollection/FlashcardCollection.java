package FlashcardCollection;

import Flashcards.Flashcard;

public class FlashcardCollection {
    private Flashcard[] flashcards;
    private FlashcardIterator iterator;
    private float[] points;
    private float pointsSum;

    public void finalizeSummary()
    {
        for (float point : points) {
            pointsSum += point;
        }
    }

    public FlashcardIterator createIterator()
    {

        return null;
    }
}
