package access_control_encapsulation;

import java.util.Arrays;

// Immutable class
public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        // Defensive copy in constructor
        if (bookIds != null) {
            this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        } else {
            this.bookIds = new String[0];
        }
    }

    public String getMemberId() {
        return memberId;
    }

    // Defensive copy in getter
    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    // Wither pattern: Returns a brand-new instance
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= this.bookIds.length) {
            return this;
        }
        String[] updatedBookIds = getBookIds(); // Creates a clean copy
        updatedBookIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedBookIds);
    }
}