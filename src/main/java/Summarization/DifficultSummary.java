package Summarization;

public class DifficultSummary implements SummaryStrategy {
    private String[] answers;


    @Override
    public float summarize(String correctAnswer, String userAnswer) {
        if (correctAnswer.toLowerCase().equals(userAnswer.toLowerCase())) return 1;
        else
        {
            float errors = findDiffs(correctAnswer, userAnswer);
            return 1 - (errors/4) > 0 ? 1 - (errors/4) : 0;
        }
    }

    //Algorytm obliczający odległość Levenshteina, czyli miary odmienności napisów
    private float findDiffs(String correctAnswer, String userAnswer)
    {
        int m = correctAnswer.length() + 1;
        int n = userAnswer.length() + 1;
        int cost;
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            matrix[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(correctAnswer.charAt(i-1) == userAnswer.charAt(j-1))
                    cost = 0;
                else
                    cost = 1;

                matrix[i][j] = Math.min(matrix[i-1][j] + 1, Math.min(matrix[i][j-1] + 1, matrix[i-1][j-1] + cost));
            }
        }

        return matrix[m-1][n-1];
    }
}
