package string.class_problems;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256]; // Array to store frequency of each ASCII character

        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        // Find the first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0'; // Return null character if none found
    }

    public static void checkAndPrint(String text) {
        char result = findFirstNonRepeatingChar(text);
        System.out.println("Input: \"" + text + "\"");
        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        checkAndPrint("swiss");
        checkAndPrint("aabbcc");
    }
}