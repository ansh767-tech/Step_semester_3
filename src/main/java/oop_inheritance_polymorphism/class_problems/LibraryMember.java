package oop_inheritance_polymorphism;

import java.util.Arrays;

public class LibraryMember {

    private static int memberCounter = 100;
    
    private final String memberId;
    private final int memberNumber;
    private final int borrowLimit;
    private int booksBorrowed;
    private final int[] fineHistory;
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId.trim();
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        this.fineHistory = new int[10];
        this.fineCount = 0;
        
        synchronized (LibraryMember.class) {
            memberCounter++;
            this.memberNumber = memberCounter;
        }
    }

    public LibraryMember(int borrowLimit) {
        synchronized (LibraryMember.class) {
            memberCounter++;
            this.memberNumber = memberCounter;
        }
        this.memberId = "LIB-" + this.memberNumber;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        this.fineHistory = new int[10];
        this.fineCount = 0;
    }

    public void borrowBook() {
        if (this.booksBorrowed < this.borrowLimit) {
            this.booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberNumber() {
        return "LIB-" + memberNumber;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (amount > 0 && fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new LibraryMember(id, borrowLimit);
                    enrolled++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Direct descendant (2 generations deep)";
        } else if (member != null) {
            return "Base Class";
        }
        return "Unknown";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        if (members != null) {
            for (LibraryMember m : members) {
                if (m != null) {
                    total += m.getBooksBorrowed();
                }
            }
        }
        return total;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (LibraryMember m : members) {
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof StudentMember) {
                        StudentMember sm = (StudentMember) m;
                        sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCounter - 100;
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int regularCount = 0;
        int facultyCount = 0;
        int nullCount = 0;

        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullCount++;
                } else if (m instanceof FacultyMember) {
                    facultyCount++;
                } else {
                    regularCount++;
                }
            }
        }

        int totalProcessed = regularCount + facultyCount;
        return totalProcessed + " processed | " + nullCount + " null skipped | " + facultyCount + " faculty | " + regularCount + " regular";
    }
}