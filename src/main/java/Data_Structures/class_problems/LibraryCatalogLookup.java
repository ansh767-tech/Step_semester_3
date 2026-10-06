package Data_Structures.class_problems;

import java.util.Arrays;
import java.util.List;

public class LibraryCatalogLookup {

    public static class BookRecord {
        private String isbn;
        private String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }
    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord currentBook = catalog.get(mid);
            int comparison = currentBook.getIsbn().compareTo(targetIsbn);

            if (comparison == 0) {
                return currentBook.getTitle();
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<BookRecord> catalog = Arrays.asList(
            new BookRecord("0001112223", "Introduction to Algebra"),
            new BookRecord("0002223334", "Beginning Python"),
            new BookRecord("0003334445", "Classic Mythology"),
            new BookRecord("0004445556", "Data and Society"),
            new BookRecord("0005556667", "European History")
        );

        System.out.println("Test 1: " + findBook(catalog, "0003334445")); // Expected: Classic Mythology
        System.out.println("Test 2: " + findBook(catalog, "0009998887")); // Expected: Not Found
    }
}