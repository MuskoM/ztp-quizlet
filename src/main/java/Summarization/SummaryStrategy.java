package Summarization;

public interface SummaryStrategy {
    float summarize(String correctAnswer, String userAnswer);
}
