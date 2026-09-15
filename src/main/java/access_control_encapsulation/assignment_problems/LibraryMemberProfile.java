package access_control_encapsulation;

public class LibraryMemberProfile {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash; // Stored write-only property

    public LibraryMemberProfile() {
        this.membershipId = null;
        this.name = "";
        this.premiumMember = false;
        this.securityAnswerHash = null;
    }

    // Write-Once Property for membershipId
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
        // Subsequent calls are silently ignored
    }

    // JavaBean Getters and Setters for standard fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    // True Write-Only Property (One-way deterministic hash, no getter exists)
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = "HASH_" + answer.hashCode();
        }
    }
}