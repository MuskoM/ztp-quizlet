package Flashcards;

import Summarization.Summarization;

import javax.swing.*;
import java.util.List;

public class Flashcard {

    protected String languageWord;
    protected String translatedWord;
    protected String answer;
    protected boolean isAnswered;
    protected boolean isAnswerCorrect;
    //TODO: Zmienić typ Object na Flashcards.FlashcardType, po zrobieniu Flyweighta
    FlashcardType type;
    protected Summarization summContext;
    protected List<String> options;

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    protected float points;

    public Flashcard() {
        summContext = new Summarization();
    }

    public Flashcard(String languageWord, String translatedWord) {
        this.translatedWord = translatedWord;
        this.languageWord = languageWord;

        summContext = new Summarization();
    }

    public Flashcard(String languageWord, String translatedWord, List<String> options) {
        this.translatedWord = translatedWord;
        this.languageWord = languageWord;
        this.options = options;
        summContext = new Summarization();
    }

    public void viewFlashcard(String canvas) {
        System.out.println("Language Word: " + languageWord + " Translated Word: " + translatedWord);
    }

    public float summarizeAnswer()
    {
        return summContext.finalizeSummary(languageWord, answer);
    }

    public JPanel getFlashcardPanel(){return new JPanel();}

    protected void randomizeAnswers(){};

    public String getLanguageWord() {
        return languageWord;
    }

    public void setLanguageWord(String languageWord) {
        this.languageWord = languageWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    protected void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswerCorrect() {
        return isAnswerCorrect;
    }


    @Override
    public String toString() {
        return  languageWord + ":"
                + translatedWord;
    }

    public void proceed() {};
}
