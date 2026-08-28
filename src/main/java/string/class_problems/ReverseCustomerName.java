package string.class_problems;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        char[] characters = customerName.toCharArray();
        String reversed = "";

        for (int i = characters.length - 1; i >= 0; i--) {
            reversed += characters[i];
        }

        return reversed;
    }

    public static void main(String[] args) {
        String originalName = "Sunil";
        String reversedName = reverseCustomerName(originalName);

        System.out.println("Original Name: " + originalName);
        System.out.println("Reversed Name: " + reversedName);
    }
}