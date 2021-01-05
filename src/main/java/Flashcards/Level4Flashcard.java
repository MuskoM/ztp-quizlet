package Flashcards;

import Summarization.DifficultSummary;

import java.util.ArrayList;
import java.util.Scanner;

public class Level4Flashcard extends FlashcardBaseDecorator{

    private  String[] answers = new String[3];
    private ArrayList<Integer> randomized_option = new ArrayList<>();

    public Level4Flashcard(Flashcard wrapee) {
        super(wrapee);
        wrapee.summContext.setSummaryStrategy(new DifficultSummary());
    }

    @Override
    public void viewFlashcard(String canvas) {
        System.out.println("Level 4");
        super.viewFlashcard(canvas);
    }

}
