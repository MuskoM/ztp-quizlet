package Summarization;

public class Summarization {
    private SummaryStrategy summaryStrategy = new EasySummary();

    public void setSummaryStrategy(SummaryStrategy newSummaryStrategy)
    {
        this.summaryStrategy = newSummaryStrategy;
    }

    public float finalizeSummary(String correctAnswer, String userAnswer)
    {
        return summaryStrategy.summarize(correctAnswer, userAnswer);
    }
}
