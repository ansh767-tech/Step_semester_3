package oop_fundamentals.class_problems;

class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public int totalCredits() {
        return this.credits + this.labCredits;
    }

    public String getCode() {
        return this.code;
    }
}

public class OverloadedCourseConstructors {
    public static void main(String[] args) {
        Course c1 = new Course("21CSC201J", "Data Structures", 4);
        Course c2 = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(c1.getCode() + " total credits: " + c1.totalCredits());
        System.out.println(c2.getCode() + " total credits: " + c2.totalCredits());
    }
}