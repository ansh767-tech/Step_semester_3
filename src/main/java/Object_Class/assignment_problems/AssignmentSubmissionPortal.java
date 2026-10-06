package Object_Class.assignment_problems;

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

abstract class Assignment {
    private String title;
    private double maxMarks;
    private int dueDateDay; // Simplified integer day format (e.g. 10 for Mar 10)

    public Assignment(String title, double maxMarks, int dueDateDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDateDay = dueDateDay;
    }

    public String getTitle() { return title; }
    public double getMaxMarks() { return maxMarks; }
    public int getDueDateDay() { return dueDateDay; }

    public abstract double calculateFinalMarks(double rawMarks, int lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, double maxMarks, int dueDateDay) {
        super(title, maxMarks, dueDateDay);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, int lateDays) {
        if (lateDays <= 0) return rawMarks;
        double penaltyPercentage = lateDays * 0.10; // 10% per day late
        double finalMarks = rawMarks - (rawMarks * penaltyPercentage);
        return Math.max(0, finalMarks);
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, double maxMarks, int dueDateDay) {
        super(title, maxMarks, dueDateDay);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, int lateDays) {
        if (lateDays <= 0) return rawMarks;
        double penaltyPercentage = lateDays * 0.20; // 20% per day late
        double finalMarks = rawMarks - (rawMarks * penaltyPercentage);
        return Math.max(0, finalMarks);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = SubmissionStatus.SUBMITTED;
        
        int lateDays = Math.max(0, submissionDay - assignment.getDueDateDay());
        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + lateDays + " days late). Status: Submitted.");
        }
    }

    public Student getStudent() { return student; }
    public Assignment getAssignment() { return assignment; }
    public SubmissionStatus getStatus() { return status; }

    public void grade(double rawMarks) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Submission already graded.");
            return;
        }

        int lateDays = Math.max(0, submissionDay - assignment.getDueDateDay());
        this.finalMarks = assignment.calculateFinalMarks(rawMarks, lateDays);
        this.status = SubmissionStatus.GRADED;

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%.0f. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks());
        } else {
            int penaltyPercent = lateDays * (assignment instanceof WrittenAssignment ? 20 : 10);
            System.out.printf("%s graded: %.0f/%.0f after %d%% late penalty. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks(), penaltyPercent);
        }
    }

    public void resubmit(int newSubmissionDay) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        this.submissionDay = newSubmissionDay;
        System.out.println(student.getName() + " resubmitted '" + assignment.getTitle() + "'.");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSub = new Submission(asha, linkedListLab, 10);
        Submission raviSub = new Submission(ravi, designEssay, 14);

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.resubmit(11);
    }
}