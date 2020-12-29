package Summarization;

public class EasySummary implements SummaryStrategy {
    private String[] answers;

    @Override
    public float summarize(String correctAnswer, String userAnswer) {
        if (correctAnswer.equals(userAnswer))
        {
            return 1;
        }
        else return 0;
    }
}
