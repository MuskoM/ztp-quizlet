package Summarization;

public class Summarization {
    private Strategy summaryStrategy;

    public void setSummaryStrategy(Strategy newStrategy)
    {
        this.summaryStrategy = newStrategy;
    }

    public void finalizeSummary()
    {
        summaryStrategy.summarize();
    }
}
