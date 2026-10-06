package access_control_encapsulation;

public class CirculationLedger {

    private static final String DEFAULT_BRANCH_CODE;

    // Static Initialization Block
    static {
        DEFAULT_BRANCH_CODE = "MAIN_BRANCH_001";
    }

    public static String getBranchCode() {
        return DEFAULT_BRANCH_CODE;
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int regularCount = 0;
        int referenceOnlyCount = 0;
        int nullCount = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullCount++;
                } else if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnlyCount++;
                } else if (receipt instanceof LoanReceipt) {
                    regularCount++;
                }
            }
        }

        int totalProcessed = regularCount + referenceOnlyCount;
        return totalProcessed + " processed | " + nullCount + " null skipped | " + referenceOnlyCount + " reference-only | " + regularCount + " regular";
    }
}