package Flashcards;

import java.util.Scanner;

public class Level4Flashcard extends FlashcardBaseDecorator{

    public String input = "PLACEHOLDER";

    public Level4Flashcard(Flashcard wrapee) {
        super(wrapee);
    }

    @Override
    public void viewFlashcard(String canvas) {
        System.out.println("Level 4");
        super.viewFlashcard(canvas);
    }

}
