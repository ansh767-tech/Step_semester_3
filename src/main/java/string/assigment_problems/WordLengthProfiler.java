package string.assigment_problems;

public class WordLengthProfiler {

    public static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");
        
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            // Remove any trailing punctuation marks for an accurate length check
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            int len = cleanWord.length();

            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.println("Input: \"" + review + "\"");
        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        String review = "This movie was absolutely fantastic and thrilling";
        classifyWordLengths(review);
    }
}