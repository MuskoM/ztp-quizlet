package Flashcards;

public class Flashcard {

    protected String languageWord;
    protected String translatedWord;
    protected String answer;
    protected boolean isAnswered;
    protected boolean isAnswerCorrect;
    //TODO: Zmienić typ Object na FlashcardType, po zrobieniu Flyweighta
    Object type;

    public Flashcard() {
    }

    public Flashcard(String languageWord, String translatedWord ){
        this.translatedWord = translatedWord;
        this.languageWord = languageWord;
    }

    public void viewFlashcard(String canvas){
        System.out.println("Language Word: " + languageWord + " Translated Word: " + translatedWord);
    }

    public String getBaseWord() {
        return languageWord;
    }

    public void setBaseWord(String baseWord) {
        this.languageWord = baseWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswerCorrect() {
        return isAnswerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        isAnswerCorrect = answerCorrect;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
