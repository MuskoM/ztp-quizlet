import Summarization.DifficultSummary;
import Summarization.SummaryStrategy;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.assertEquals;

@Category(GlobalTests.class)
public class SummaryTests {

    @Test
    public void testLevenshteinDistanceAlgorithm()
    {
        SummaryStrategy summary = new DifficultSummary();
        float first = summary.summarize("dog", "dog");
        assertEquals(first, 1, 0);
        float second = summary.summarize("dog", "dogg");
        assertEquals(second, 0.75, 0);
        float third = summary.summarize("dog", "god");
        assertEquals(third, 0.5, 0);
        float fourth = summary.summarize("dog", "dg");
        assertEquals(fourth, 0.75, 0);
        float fifth = summary.summarize("dog", "hell");
        assertEquals(fifth, 0, 0);
    }
}
