package oop_inheritance_polymorphism;

import java.util.Arrays;

public class GymMember {

    private static int memberCounter = 2000;

    private final String memberId;
    public final String membershipNumber;
    private final int monthlyFee;
    private int sessionsAttended;
    private int feesPaid;
    private final int[] lateFeeHistory;
    private int lateFeeCount;

    // Constructors
    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId.trim();
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
        this.feesPaid = 0;
        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;

        synchronized (GymMember.class) {
            memberCounter++;
            this.membershipNumber = "GYM-" + memberCounter;
        }
    }

    public GymMember(int monthlyFee) {
        synchronized (GymMember.class) {
            memberCounter++;
            this.membershipNumber = "GYM-" + memberCounter;
        }
        this.memberId = this.membershipNumber;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
        this.feesPaid = 0;
        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;
    }

    // Attendance & Fee methods
    public void attendSession() {
        this.sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void payFee(int amount) {
        if (amount > 0) {
            this.feesPaid += amount;
        }
    }

    public void payFee(int amount, String mode) {
        // Mode handling if needed, then delegate internally
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    // Late fee management & Defensive copying
    protected void chargeLateFee(int amount) {
        if (amount > 0 && lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }

    // Static Utility Methods
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct descendant (2 generations deep)";
        } else if (member != null) {
            return "Base Class";
        }
        return "Unknown";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    total += m.getSessionsAttended();
                }
            }
        }
        return total;
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) m;
                        sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCounter - 2000;
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int individualCount = 0;
        int groupCount = 0;
        int nullCount = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullCount++;
                } else if (m instanceof GroupClassMember) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        int totalProcessed = individualCount + groupCount;
        return totalProcessed + " processed | " + nullCount + " null skipped | " + groupCount + " group " + individualCount + " individual";
    }
}