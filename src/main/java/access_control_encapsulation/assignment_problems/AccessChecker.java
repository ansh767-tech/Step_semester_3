package access_control_encapsulation;

public class AccessChecker {

    // Appropriately scoped fields for LibraryMember demonstration
    public String memberName;        // Public: accessible anywhere
    protected double finesOwed;     // Protected: accessible in package & subclasses
    int borrowedBooksCount;         // Default/Package-private: accessible within same package
    private String memberSecretPin;  // Private: accessible only inside LibraryMember

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

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null || attempts.length == 0) {
            return "None Denied";
        }

        for (int i = 0; i < attempts.length; i++) {
            String[] attempt = attempts[i];
            if (attempt != null && attempt.length >= 2) {
                String modifier = attempt[0];
                String context = attempt[1];
                String result = classifyAccess(modifier, context);

                if ("DENIED".equals(result)) {
                    return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
                }
            }
        }

        return "None Denied";
    }
}