package access_control_encapsulation;

public class AccessChecker {

    // Appropriately scoped fields for MovieTicket
    public String movieTitle;       // Public: accessible anywhere
    protected double ticketPrice;   // Protected: accessible in package & subclasses
    int screenId;                   // Default/Package-private: accessible within same package
    private String seatNumber;      // Private: accessible only inside MovieTicket

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || 
                    "SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) || 
                    "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowedCount = 0;
        int deniedCount = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String result = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(result)) {
                        allowedCount++;
                    } else {
                        deniedCount++;
                    }
                }
            }
        }

        return "Allowed: " + allowedCount + " | Denied: " + deniedCount;
    }
}