package access_control_encapsulation;

public class SubclassTicketAccess {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        return AccessChecker.classifyAccess(fieldModifier, accessorContext);
    }
}